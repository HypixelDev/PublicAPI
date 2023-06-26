package net.hypixel.api.reactor;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.hypixel.api.http.HypixelHttpClient;
import net.hypixel.api.http.HypixelHttpResponse;
import net.hypixel.api.http.RateLimit;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.client.HttpClientResponse;
import reactor.util.function.Tuple3;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReactorHttpClient implements HypixelHttpClient {

    private final HttpClient httpClient;
    private final UUID apiKey;

    // Marker to reset the request counter and release waiting threads
    private final AtomicBoolean firstRequestReturned = new AtomicBoolean(false);
    // Marker to only schedule a reset clock once on error 429
    private final AtomicBoolean overflowStartedNewClock = new AtomicBoolean(false);

    // Callbacks that will trigger their corresponding requests
    private final ArrayBlockingQueue<RequestCallback> blockingQueue;

    // For shutting down the flux that emits request callbacks
    private final Disposable requestCallbackFluxDisposable;
    private final ExecutorService requestCallbackFluxExecutorService = Executors.newSingleThreadExecutor();

    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition limitResetCondition = lock.newCondition();

    /*
     * How many requests we can send before reaching the limit
     * Starts as 1 so the first request returns and resets this value before allowing other requests to be sent.
     */
    private int actionsLeftThisMinute = 1;

    /**
     * Constructs a new instance of this client using the specified API key.
     *
     * @param apiKey                  the key associated with this connection
     * @param minDelayBetweenRequests minimum time between sending requests (in ms) default is 8
     * @param bufferCapacity          fixed size of blockingQueue
     */
    public ReactorHttpClient(UUID apiKey, long minDelayBetweenRequests, int bufferCapacity) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.create().secure();
        this.blockingQueue = new ArrayBlockingQueue<>(bufferCapacity);

        this.requestCallbackFluxDisposable = Flux.<RequestCallback>generate((synchronousSink) -> {
            try {
                RequestCallback callback = blockingQueue.take();
                // prune skipped/completed requests to avoid counting them
                while (callback.isCanceled()) {
                    callback = blockingQueue.take();
                }

                this.decrementActionsOrWait();

                synchronousSink.next(callback);
            } catch (InterruptedException e) {
                throw new AssertionError("This should not have been possible", e);
            }
        }).subscribeOn(Schedulers.fromExecutorService(this.requestCallbackFluxExecutorService)).delayElements(Duration.ofMillis(minDelayBetweenRequests), Schedulers.boundedElastic()).subscribe(RequestCallback::sendRequest);
    }

    public ReactorHttpClient(UUID apiKey, long minDelayBetweenRequests) {
        this(apiKey, minDelayBetweenRequests, 500);
    }

    public ReactorHttpClient(UUID apiKey, int bufferCapacity) {
        this(apiKey, 8, bufferCapacity);
    }

    public ReactorHttpClient(UUID apiKey) {
        this(apiKey, 8, 500);
    }

    /**
     * Canceling the returned future will result in canceling the sending of the request if still possible
     */
    @Override
    public CompletableFuture<HypixelHttpResponse> makeRequest(String url) {
        return toHypixelResponseFuture(makeRequest(url, false));
    }

    /**
     * Canceling the returned future will result in canceling the sending of the request if still possible
     */
    @Override
    public CompletableFuture<HypixelHttpResponse> makeAuthenticatedRequest(String url) {
        return toHypixelResponseFuture(makeRequest(url, true));
    }

    private static CompletableFuture<HypixelHttpResponse> toHypixelResponseFuture(Mono<Tuple3<String, Integer, RateLimit>> result) {
        return result.map(tuple -> new HypixelHttpResponse(tuple.getT2(), tuple.getT1(), tuple.getT3()))
                .toFuture();
    }

    @Override
    public void shutdown() {
        this.requestCallbackFluxDisposable.dispose();
        this.requestCallbackFluxExecutorService.shutdown();
    }

    /**
     * Makes a request to the Hypixel api and returns a {@link Mono} containing
     * the response body and status code, canceling this mono will prevent the request from being sent if possible
     *
     * @param path            full url
     * @param isAuthenticated whether to enable authentication or not
     */
    public Mono<Tuple3<String, Integer, RateLimit>> makeRequest(String path, boolean isAuthenticated) {
        return Mono.create(sink -> {
            RequestCallback callback = new RequestCallback(path, sink, isAuthenticated, this);

            try {
                this.blockingQueue.put(callback);
            } catch (InterruptedException e) {
                sink.error(e);
                throw new AssertionError("Queue insertion interrupted. This should not have been possible", e);
            }
        });
    }

    private void decrementActionsOrWait() throws InterruptedException {
        this.lock.lock();
        try {
            while (this.actionsLeftThisMinute <= 0) {
                this.limitResetCondition.await();
            }
            this.actionsLeftThisMinute--;
        } finally {
            this.lock.unlock();
        }
    }


    private void incrementActionsLeftThisMinute() {
        this.lock.lock();
        try {
            this.actionsLeftThisMinute++;
            this.limitResetCondition.signal();
        } finally {
            this.lock.unlock();
        }
    }

    private void setActionsLeftThisMinute(int value) {
        this.lock.lock();
        try {
            this.actionsLeftThisMinute = Math.max(0, value);
            this.limitResetCondition.signal();
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * Reads response status and retries error 429 (too many requests)
     * The first request after every limit reset will be used to schedule the next limit reset
     *
     * @param response        the {@link HttpClientResponse} from our request
     * @param requestCallback the callback controlling our request
     * @return whether to return the request body or wait for a retry
     */
    private ResponseHandlingResult handleResponse(HttpClientResponse response, RequestCallback requestCallback) throws InterruptedException {
        if (response.status() == HttpResponseStatus.TOO_MANY_REQUESTS) {
            System.out.println("Too many requests were sent, is something else using the same API Key?!!");
            int timeRemaining = Math.max(1, response.responseHeaders().getInt("ratelimit-reset", 10));

            if (this.overflowStartedNewClock.compareAndSet(false, true)) {
                this.setActionsLeftThisMinute(0);
                resetForFirstRequest(timeRemaining);
            }

            // execute this last to prevent a possible exception from messing up our clock synchronization
            this.blockingQueue.put(requestCallback);
            return new ResponseHandlingResult(false, response.status().code(), null);
        }

        int limit = Math.max(1, response.responseHeaders().getInt("ratelimit-limit", 10));
        int timeRemaining = Math.max(1, response.responseHeaders().getInt("ratelimit-reset", 10));
        int requestsRemaining = response.responseHeaders().getInt("ratelimit-remaining", 110);
        RateLimit rateLimit = new RateLimit(limit, requestsRemaining, timeRemaining);

        if (this.firstRequestReturned.compareAndSet(false, true)) {
            this.setActionsLeftThisMinute(requestsRemaining);
            resetForFirstRequest(timeRemaining);
        }

        return new ResponseHandlingResult(true, response.status().code(), rateLimit);
    }

    /**
     * Wakes up all waiting threads in the specified amount of seconds
     * (Adds two seconds to account for sync errors in the server).
     *
     * @param timeRemaining how much time is left until the next reset
     */
    private void resetForFirstRequest(int timeRemaining) {
        Schedulers.parallel().schedule(() -> {
            this.firstRequestReturned.set(false);
            this.overflowStartedNewClock.set(false);
            this.setActionsLeftThisMinute(1);
        }, timeRemaining + 2, TimeUnit.SECONDS);
    }

    /**
     * Controls a request
     */
    private static class RequestCallback {
        private final String url;
        private final MonoSink<Tuple3<String, Integer, RateLimit>> requestResultSink;
        private final ReactorHttpClient requestRateLimiter;
        private final boolean isAuthenticated;
        private final ReentrantLock lock = new ReentrantLock();
        private boolean isCanceled = false;

        private RequestCallback(String url, MonoSink<Tuple3<String, Integer, RateLimit>> requestResultSink, boolean isAuthenticated, ReactorHttpClient requestRateLimiter) {
            this.url = url;
            this.requestResultSink = requestResultSink;
            this.requestRateLimiter = requestRateLimiter;
            this.isAuthenticated = isAuthenticated;

            this.requestResultSink.onCancel(this::setCanceled);
        }

        private void setCanceled() {
            this.lock.lock();
            try {
                this.isCanceled = true;
            } finally {
                this.lock.unlock();
            }
        }

        public boolean isCanceled() {
            this.lock.lock();
            try {
                return this.isCanceled;
            } finally {
                this.lock.unlock();
            }
        }

        private void sendRequest() {
            if (this.isCanceled()) {
                this.requestRateLimiter.incrementActionsLeftThisMinute();
                return;
            }

            (this.isAuthenticated ? requestRateLimiter.httpClient.headers(headers -> headers.add("API-Key", requestRateLimiter.apiKey.toString())) : requestRateLimiter.httpClient).get()
                    .uri(url)
                    .responseSingle((response, body) -> {
                        try {
                            ResponseHandlingResult result = requestRateLimiter.handleResponse(response, this);
                            if (result.allowToPass) {
                                return Mono.zip(body.asString(), Mono.just(result.statusCode), Mono.just(result.rateLimit));
                            }
                            return Mono.empty();
                        } catch (InterruptedException e) {
                            this.requestResultSink.error(e);
                            throw new AssertionError("ERROR: Queue insertion got interrupted, serious problem! (this should not happen!!)", e);
                        }
                    }).subscribe(this.requestResultSink::success);
        }
    }

    /**
     * Data object
     */
    private static class ResponseHandlingResult {
        public final boolean allowToPass;
        public final int statusCode;
        public final RateLimit rateLimit;

        public ResponseHandlingResult(boolean allowToPass, int statusCode, RateLimit rateLimit) {
            this.allowToPass = allowToPass;
            this.statusCode = statusCode;
            this.rateLimit = rateLimit;
        }
    }
}
