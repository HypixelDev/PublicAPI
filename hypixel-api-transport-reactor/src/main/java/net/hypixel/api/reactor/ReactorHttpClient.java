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

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class ReactorHttpClient implements HypixelHttpClient {
    private final HttpClient httpClient;
    private final UUID apiKey;

    // Marker to reset the request counter and release waiting threads
    private final AtomicBoolean firstRequestReturned = new AtomicBoolean(false);
    // Marker to only schedule a reset clock once on error 429
    private final AtomicBoolean overflowStartedNewClock = new AtomicBoolean(false);

    /*
     * How many requests we can send before reaching the limit
     * Starts as 1 so the first request returns and resets this value before allowing other requests to be sent.
     */
    private final AtomicInteger requestsLeft = new AtomicInteger(1);
    // Callbacks that will trigger their corresponding requests
    private final ArrayBlockingQueue<RequestCallback> blockingQueue = new ArrayBlockingQueue<>(500);

    // For shutting down the flux that emits request callbacks
    private final Disposable requestCallbackFluxDisposable;

    // For signaling limit reset
    private final BlockingMonoCallback blockingMonoCallback = new BlockingMonoCallback();
    // Emits a value when limit gets reset to wake up blocked threads
    private final Mono<Boolean> blockingMonoForRequests = Mono.create(this.blockingMonoCallback);

    /**
     * Constructs a new instance of this client using the specified API key.
     *
     * @param apiKey the key associated with this connection
     */
    public ReactorHttpClient(UUID apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.create().secure();

        this.requestCallbackFluxDisposable = Flux.<RequestCallback>generate((synchronousSink) -> {
            try {
                RequestCallback callback = blockingQueue.take();
                // prune skipped/completed requests to avoid counting them
                while (callback.isCompleted) {
                    callback = blockingQueue.take();
                }
                if (this.requestsLeft.updateAndGet(amt -> amt - 1) < 0) {
                    this.blockingMonoForRequests.block();
                }
                synchronousSink.next(callback);
            } catch (InterruptedException e) {
                throw new AssertionError("This should not have been possible", e);
            }
        }).subscribeOn(Schedulers.boundedElastic()).delayElements(Duration.ofMillis(50), Schedulers.boundedElastic()).subscribe(RequestCallback::sendRequest);
    }

    @Override
    public CompletableFuture<HypixelHttpResponse> makeRequest(String url) {
        return toHypixelResponseFuture(makeRequest(url, false));
    }

    @Override
    public CompletableFuture<HypixelHttpResponse> makeAuthenticatedRequest(String url) {
        // this ignores the possibility of canceling the request
        return toHypixelResponseFuture(makeRequest(url, true));
    }

    private static CompletableFuture<HypixelHttpResponse> toHypixelResponseFuture(RequestResultMono result) {
        return result.getRequestResult()
                .map(tuple -> new HypixelHttpResponse(tuple.getT2(), tuple.getT1()))
                .toFuture();
    }

    @Override
    public void shutdown() {
        this.requestCallbackFluxDisposable.dispose();
    }

    /**
     * Makes a request to the Hypixel api and returns a {@link RequestResultMono} containing
     * a {@link Mono<String>} that emits the request's body on success and a {@link RequestCallback} that can be used to cancel
     * this request to prevent pending requests from adding to the rate limit.
     * @param path full url
     */
    public RequestResultMono makeRequest(String path, boolean isAuthenticated) {
        AtomicReference<RequestCallback> reference = new AtomicReference<>();
        return new RequestResultMono(Mono.<Tuple2<String, Integer>>create(sink -> {
            RequestCallback callback = new RequestCallback(path, sink, isAuthenticated, this);
            reference.set(callback);

            try {
                this.blockingQueue.put(callback);
            } catch (InterruptedException e) {
                callback.execute(false);
                throw new AssertionError("Queue insertion interrupted. This should not have been possible", e);
            }
        }).subscribeOn(Schedulers.boundedElastic()), reference);
    }

    /**
     * Reads response status and retries error 429 (too many requests)
     * The first request after every limit reset will be used to schedule the next limit reset
     *
     * @param response the {@link HttpClientResponse} from our request
     * @param requestCallback the callback controlling our request
     * @return whether to return the request body or wait for a retry
     */
    private ResponseHandlingResult handleResponse(HttpClientResponse response, RequestCallback requestCallback) throws InterruptedException {
        if (response.status() == HttpResponseStatus.TOO_MANY_REQUESTS) {
            int timeRemaining = response.responseHeaders().getInt("ratelimit-reset");
            if (this.overflowStartedNewClock.compareAndSet(false, true)) {
                this.requestsLeft.set(0);
                resetForFirstRequest(timeRemaining);
            }

            // execute this last to prevent a possible exception from messing up our clock synchronization
            this.blockingQueue.put(requestCallback);
            return new ResponseHandlingResult(false, response.status().code());
        }

        if (this.firstRequestReturned.compareAndSet(false, true)) {
            int timeRemaining = response.responseHeaders().getInt("ratelimit-reset");
            int requestsRemaining = response.responseHeaders().getInt("ratelimit-remaining");

            this.requestsLeft.set(requestsRemaining);
            this.blockingMonoCallback.triggerRelease();

            resetForFirstRequest(timeRemaining);
        }
        return new ResponseHandlingResult(true, response.status().code());
    }

    /**
     * Wakes up all waiting threads in the specified amount of seconds (Adds two seconds to account for
     * sync errors in the server).
     *
     * @param timeRemaining how much time is left until the next reset
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
     * Includes the request body as {@link Mono<String>} and the callback that controls this request
     */
    public static class RequestResultMono {
        private AtomicReference<RequestCallback> callbackReference;
        private Mono<Tuple2<String, Integer>> requestResult;

        public RequestResultMono(Mono<Tuple2<String, Integer>> requestResult, AtomicReference<RequestCallback> callbackReference) {
            this.callbackReference = callbackReference;
            this.requestResult = requestResult;
        }

        public AtomicReference<RequestCallback> getCallbackReference() {
            return this.callbackReference;
        }

        public Mono<Tuple2<String, Integer>> getRequestResult() {
            return this.requestResult;
        }
    }

    /**
     * Wakes up waiting threads
     */
    private static class BlockingMonoCallback implements Consumer<MonoSink<Boolean>> {
        private MonoSink<Boolean> sinkReference = null;

        @Override
        public void accept(MonoSink<Boolean> booleanMonoSink) {
            sinkReference = booleanMonoSink;
        }

        public void triggerRelease() {
            if (this.sinkReference != null) {
                this.sinkReference.success(true);
            }
        }
    }

    /**
     * Controls a request
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

            monoSink.onDispose(() -> this.isCompleted = true);
        }

        public boolean isCompleted() {
            return this.isCompleted;
        }

        public void execute(Boolean shouldRequest) {
            if (isCompleted) return;

            if (shouldRequest) {
                (this.isAuthenticated ? requestRateLimiter.httpClient.headers(headers -> headers.add("API-Key", requestRateLimiter.apiKey.toString())) : requestRateLimiter.httpClient).get()
                        .uri(url)
                        .responseSingle((response, body) -> {
                            try {
                                ResponseHandlingResult result = requestRateLimiter.handleResponse(response, this);

                                if (result.allowToPass) {
                                    return body.asString().zipWith(Mono.just(result.statusCode));
                                }
                                return Mono.empty();
                            } catch (InterruptedException e) {
                                monoSink.success();
                                throw new AssertionError("ERROR: Queue insertion got interrupted, serious problem! (this should not happen!!)", e);
                            }
                        }).subscribe(this.monoSink::success);
                return;
            }
            this.monoSink.success();
        }

        public void cancel() {
            this.execute(false);
        }

        private void sendRequest() {
            this.execute(true);
        }
    }

    /**
     * Data object
     */
    private static class ResponseHandlingResult {
        public final boolean allowToPass;
        public final int statusCode;

        public ResponseHandlingResult(boolean allowToPass, int statusCode) {
            this.allowToPass = allowToPass;
            this.statusCode = statusCode;
        }
    }
}
