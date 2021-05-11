package net.hypixel.api.reactor;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.hypixel.api.http.HypixelHttpClient;
import net.hypixel.api.http.HypixelHttpResponse;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.client.HttpClientResponse;
import reactor.util.function.Tuple2;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class ReactorHttpClient implements HypixelHttpClient {
    /**
     * The {@link HttpClient} used to send requests with
     */
    private final HttpClient httpClient;
    private final UUID apiKey;

    /**
     * marker to reset {@link ReactorHttpClient#requestsLeft} and release waiting threads by triggering {@link BlockingMonoCallback#triggerRelease()}
     */
    private final AtomicBoolean firstRequestReturned = new AtomicBoolean(false);
    private final AtomicBoolean overflowStartedNewClock = new AtomicBoolean(false);
    /**
     * AtomicInteger keeping track of how many requests we can send until we hit the limit
     * (initialized with 1 so the first request will return and reset this value before allowing other requests to be sent)
     */
    private final AtomicInteger requestsLeft = new AtomicInteger(1);
    /**
     * A {@link ArrayBlockingQueue< RequestCallback >} to hold {@link RequestCallback} callbacks that will trigger the corresponding requests
     * this is rate limited by the {@link Flux} created and subscribed to in the constructor (buffers 500 requests and will cause blocking when full)
     */
    private final ArrayBlockingQueue<RequestCallback> blockingQueue = new ArrayBlockingQueue<>(500);

    /**
     * A {@link Disposable} that shuts down the flux that emits callbacks to send
     */
    private final Disposable requestCallbackFluxDisposable;
    /**
     * A callback that will cause threads waiting to try sending a request to wake up
     */
    private final BlockingMonoCallback blockingMonoCallback = new BlockingMonoCallback();
    /**
     * The {@link Mono} associated with {@link ReactorHttpClient#blockingMonoCallback} that will emit a value when the next reset happens
     */
    private final Mono<Boolean> blockingMonoForRequests = Mono.create(this.blockingMonoCallback);

    /**
     * Constructor taking in an apiKey
     *
     * This will initialize the {@link HttpClient} and subscribe to a {@link Flux< RequestCallback >} that
     * delays sending requests when the rate limit has been reached until the next reset
     * @param apiKey
     */
    public ReactorHttpClient(UUID apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.create().secure();

        this.requestCallbackFluxDisposable = Flux.<RequestCallback>generate((synchronousSink) -> {
            try {
                // blocks till next request callback has been found
                RequestCallback callback = blockingQueue.take();
                // we don't want skipped requests to decrease our limit
                while (callback.isCompleted) {
                    callback = blockingQueue.take();
                }
                // trigger waiting when the limit has been reached
                if (this.requestsLeft.updateAndGet(amt -> amt - 1) < 0) {
                    this.blockingMonoForRequests.block();
                }
                // pass the next request callback to this flux's SynchronousSink
                synchronousSink.next(callback);
            } catch (InterruptedException e) {
                // this error should never happen
                // how could this be improved?
                e.printStackTrace();
            }
            // we subscribe on a blocking thread and signal the next callback to send the associated request
        }).subscribeOn(Schedulers.boundedElastic()).subscribe(RequestCallback::sendRequest);
    }

    @Override
    public CompletableFuture<HypixelHttpResponse> makeRequest(String url) {
        return this.makeRequest(url, false).getRequestResult()
                .map(tuple -> new HypixelHttpResponse(tuple.getT2(), tuple.getT1()))
                .toFuture();
    }

    @Override
    public CompletableFuture<HypixelHttpResponse> makeAuthenticatedRequest(String url) {
        // this ignores the possibility of canceling the request
        return this.makeRequest(url, true).getRequestResult()
                .map(tuple -> new HypixelHttpResponse(tuple.getT2(), tuple.getT1()))
                .toFuture();
    }

    @Override
    public void shutdown() {
        // cancel subscription to end thread
        this.requestCallbackFluxDisposable.dispose();
    }

    /**
     * Makes a request to the Hypixel api and returns a {@link RequestResultMono} containing
     * a {@link Mono<String>} that emits the request's body on success and a {@link Consumer<Boolean>} that can be used to cancel
     * this request to prevent waiting requests from being useless and eating up our precious limit
     *
     * (not sure how that would be implemented in the hypixel api!!)
     * @param path full url
     */
    public RequestResultMono makeRequest(String path, boolean isAuthenticated) {
        // create a reference to this request's cancel callback
        AtomicReference<RequestCallback> reference = new AtomicReference<>();
        return new RequestResultMono(Mono.<Tuple2<String, Integer>>create(sink -> {
            // create a callback that can trigger or cancel this request
            RequestCallback callback = new RequestCallback(path, sink, isAuthenticated, this);
            reference.set(callback);

            try {
                // add this callback to our blocking queue
                this.blockingQueue.put(callback);
            } catch (InterruptedException e) {
                // cancel our request silently
                // should this somehow send the error downstream? (this point should never be reached though)
                callback.execute(false);
                System.out.println("ERROR: Queue insertion got interrupted, serious problem! (this should not happen!!)");
            }
        }).subscribeOn(Schedulers.boundedElastic()), reference);
    }

    /**
     * Our response handler that will retry our first request if it's over our limit (after every reset),
     * read when the next reset happens and start a clock that will reset our limit after that time
     *
     * @param response the {@link HttpClientResponse} from our request
     * @param requestCallback the callback associated with this request, used to re-add this request to our queue upon
     *                              too many requests exception
     * @return a boolean signaling our request body whether to emit the response content or wait for rescheduling in case of a too many
     * requests error
     * @throws InterruptedException should never happen, don't know when it would
     */
    private ResponseHandlingResult handleResponse(HttpClientResponse response, RequestCallback requestCallback) throws InterruptedException {
        if (response.status() == HttpResponseStatus.TOO_MANY_REQUESTS) {
            // start clock for next reset
            int timeRemaining = response.responseHeaders().getInt("ratelimit-reset");
            if (this.overflowStartedNewClock.compareAndSet(false, true)) {
                // stop further requests from being sent
                this.requestsLeft.set(0);
                // start clock for next reset
                resetForFirstRequest(timeRemaining);
            }

            // execute this last to prevent a possible exception from messing up our clock synchronization
            this.blockingQueue.put(requestCallback);
            // tell the makeRequest returned mono to wait for the actual body instead of an error code
            return new ResponseHandlingResult(false, response.status().code());
        }

        // only trigger this code after every reset (and when starting the program)
        if (this.firstRequestReturned.compareAndSet(false, true)) {
            int timeRemaining = response.responseHeaders().getInt("ratelimit-reset");
            int requestsRemaining = response.responseHeaders().getInt("ratelimit-remaining");

            // reset request limit
            this.requestsLeft.set(requestsRemaining);
            // wake up waiting threads
            this.blockingMonoCallback.triggerRelease();

            // start clock for next reset
            resetForFirstRequest(timeRemaining);
        }
        // tell the makeRequest returned mono to emit the body of the request
        return new ResponseHandlingResult(true, response.status().code());
    }

    /**
     * A helper function to reset the limit and trigger waiting threads to wake up
     * (has 2 seconds margin to account for errors from the server's clock)
     * @param timeRemaining
     */
    private void resetForFirstRequest(int timeRemaining) {
        Schedulers.parallel().schedule(() -> {
            this.firstRequestReturned.set(false);
            this.overflowStartedNewClock.set(false);
            this.requestsLeft.set(1);
            this.blockingMonoCallback.triggerRelease();
        }, timeRemaining + 2, TimeUnit.SECONDS);
    }

    /**
     * A result class that includes both the resulting request (as a {@link Mono<String>})
     * and a {@link AtomicReference<Consumer<Boolean>>} callback to cancel this request with
     */
    public static class RequestResultMono {
        /**
         * Callback to optionally cancel this request
         */
        private AtomicReference<RequestCallback> callbackReference;
        private Mono<Tuple2<String, Integer>> requestResult;

        public RequestResultMono(Mono<Tuple2<String, Integer>> requestResult, AtomicReference<RequestCallback> callbackReference) {
            this.callbackReference = callbackReference;
            this.requestResult = requestResult;
        }

        // useful for canceling requests, not supported by hypixel-api currently
        public AtomicReference<RequestCallback> getCallbackReference() {
            return this.callbackReference;
        }

        public Mono<Tuple2<String, Integer>> getRequestResult() {
            return this.requestResult;
        }
    }

    /**
     * A helper class to notify waiting threads
     */
    private static class BlockingMonoCallback implements Consumer<MonoSink<Boolean>> {
        private MonoSink<Boolean> sinkReference = null;

        /**
         * Gets reference to the waiting Mono's monoSink
         * @param booleanMonoSink
         */
        @Override
        public void accept(MonoSink<Boolean> booleanMonoSink) {
            sinkReference = booleanMonoSink;
        }

        /**
         * Trigger current waiting thread to continue
         */
        public void triggerRelease() {
            if (this.sinkReference != null) {
                this.sinkReference.success(true);
            }
        }
    }

    /**
     * A helper class that handles sending the actual request
     */
    private static class RequestCallback {
        private final String url;
        private final MonoSink<Tuple2<String, Integer>> monoSink;
        private final ReactorHttpClient requestRateLimiter;
        private final boolean isAuthenticated;
        private boolean isCompleted = false;

        public RequestCallback(String url, MonoSink<Tuple2<String, Integer>> monoSink, boolean isAuthenticated, ReactorHttpClient requestRateLimiter) {
            this.url = url;
            this.monoSink = monoSink;
            this.requestRateLimiter = requestRateLimiter;
            this.isAuthenticated = isAuthenticated;

            // prevent from resending the request after been completed
            monoSink.onDispose(() -> this.isCompleted = true);
        }

        public boolean isCompleted() {
            return this.isCompleted;
        }

        /**
         * Either send the request or return {@link Mono#empty()}
         * @param shouldRequest
         */
        public void execute(Boolean shouldRequest) {
            if (isCompleted) return;

            if (shouldRequest) {
                // add headers for authentication
                (this.isAuthenticated ? requestRateLimiter.httpClient.headers(headers -> headers.add("API-Key", requestRateLimiter.apiKey.toString())) : requestRateLimiter.httpClient).get()
                        .uri(url)
                        .responseSingle((response, body) -> {
                            try {
                                // handle response and get back whether to return this request's body or wait
                                ResponseHandlingResult result = requestRateLimiter.handleResponse(response, this);

                                // body handling
                                if (result.allowToPass) {
                                    return body.asString().zipWith(Mono.just(result.statusCode));
                                }
                                return Mono.empty();
                            } catch (InterruptedException e) {
                                // silently skip request
                                monoSink.success();
                                System.out.println("ERROR: Queue insertion got interrupted, serious problem! (this should not happen!!)");
                                return Mono.empty();
                            }
                        }).subscribe(this.monoSink::success);
                return;
            }
            this.monoSink.success();
        }

        public void cancel() {
            this.execute(false);
        }

        // should only be called by ReactorHttpClient, hence private
        private void sendRequest() {
            this.execute(true);
        }
    }

    private static class ResponseHandlingResult {
        public final boolean allowToPass;
        public final int statusCode;

        public ResponseHandlingResult(boolean allowToPass, int statusCode) {
            this.allowToPass = allowToPass;
            this.statusCode = statusCode;
        }
    }
}
