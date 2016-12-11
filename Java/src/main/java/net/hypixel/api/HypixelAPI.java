package net.hypixel.api;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.hypixel.api.adapters.DateTimeTypeAdapter;
import net.hypixel.api.adapters.GameTypeTypeAdapter;
import net.hypixel.api.adapters.GuildCoinHistoryAdapter;
import net.hypixel.api.adapters.GuildCoinHistoryHoldingTypeAdapterFactory;
import net.hypixel.api.adapters.UUIDTypeAdapter;
import net.hypixel.api.exceptions.APIThrottleException;
import net.hypixel.api.exceptions.HypixelAPIException;
import net.hypixel.api.reply.AbstractReply;
import net.hypixel.api.reply.GuildReply;
import net.hypixel.api.request.Request;
import net.hypixel.api.util.Callback;
import net.hypixel.api.util.GameType;

@SuppressWarnings("unused")
public class HypixelAPI {

    private static HypixelAPI instance;
    private final Gson gson;
    private final ReentrantReadWriteLock lock;
    private final ExecutorService exService = Executors.newCachedThreadPool();
    private final HttpClient httpClient;
    private UUID apiKey;

    private HypixelAPI() {
        gson = new GsonBuilder()
                .registerTypeAdapter(UUID.class, new UUIDTypeAdapter())
                .registerTypeAdapter(GameType.class, new GameTypeTypeAdapter())
                .registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter())

                // guilds
                .registerTypeAdapter(GuildReply.Guild.GuildCoinHistory.class, new GuildCoinHistoryAdapter())
                .registerTypeAdapterFactory(new GuildCoinHistoryHoldingTypeAdapterFactory<>(GuildReply.Guild.class))
                .registerTypeAdapterFactory(new GuildCoinHistoryHoldingTypeAdapterFactory<>(GuildReply.Guild.Member.class))

                .create();
        lock = new ReentrantReadWriteLock();
        httpClient = HttpClientBuilder.create().build();
    }

    /**
     * Gets the existing HypixelAPI, or constructs a new one
     *
     * @return The HypixelAPI
     */
    public static HypixelAPI getInstance() {
        if (instance == null) {
            instance = new HypixelAPI();
        }
        return instance;
    }

    /**
     * Call this method when you're finished requesting anything from the API.
     * The API maintains it's own internal threadpool, so if you don't call this
     * the application will never exit.
     */
    public void finish() {
        try {
            exService.shutdown();
            instance = null;
        } catch (Exception e) {
            throw new HypixelAPIException(e);
        }
    }

    /**
     * @return currently set API key
     */
    public UUID getApiKey() {
        return apiKey;
    }

    /**
     * Call this method to set the API key
     *
     * @param apiKey The API key to set
     */
    public void setApiKey(UUID apiKey) {
        Preconditions.checkNotNull(apiKey);
        lock.writeLock().lock();
        try {
            this.apiKey = apiKey;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Use this method to pull data off of the Public API
     *
     * @param request Request object to pull from API
     * @param <R>     The class of the reply
     * @throws HypixelAPIException
     */
    public <R extends AbstractReply> R getSync(Request request) throws HypixelAPIException {
        lock.readLock().lock();
        SyncCallback<R> callback = new SyncCallback<>();
        try {
            if (doKeyCheck(callback)) {
                Future<HttpResponse> future = get(request, callback);
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            callback.callback(e, null);
        } finally {
            lock.readLock().unlock();
        }
        if (callback.failCause != null) {
            throw new HypixelAPIException(callback.failCause);
        } else {
            return callback.result;
        }
    }

    /**
     * Execute Request asynchronously, executes Callback when finished
     *
     * @param request  Request to get
     * @param callback Callback to execute
     * @param <R>      Class of the reply
     */
    public <R extends AbstractReply> void getAsync(Request request, Callback<R> callback) {
        lock.readLock().lock();
        try {
            if (doKeyCheck(callback)) {
                get(request, callback);
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Internal method
     *
     * @param callback The callback to fail to
     * @return True if we should continue
     */
    private boolean doKeyCheck(Callback<?> callback) {
        if (apiKey == null) {
            callback.callback(new HypixelAPIException("API key hasn't been set yet!"), null);
            return false;
        } else {
            return true;
        }
    }

      /**
     * Internal method
     *
     * @param request The request to get
     * @param callback The callback to execute
     * @param <T>      The class of the callback
     * @return The ResponseHandler that wraps the callback
     */
    @SuppressWarnings("unchecked")
    private <T extends AbstractReply> ResponseHandler<HttpResponse> buildResponseHandler(Request request, Callback<T> callback) {
        return obj -> {
            T value;
            try {
                String content = EntityUtils.toString(obj.getEntity(), "UTF-8");
                value = gson.fromJson(content, (Class<T>) request.getRequestType().getReplyClass());

                checkReply(value);
            } catch (Throwable t) {
                callback.callback(t, null);
                return obj;
            }
            callback.callback(null, value);
            return obj;
        };
    }

    /**
     * Checks reply and throws appropriate exceptions based on it's content
     *
     * @param reply The reply to check
     * @param <T>   The class of the reply
     */
    public <T extends AbstractReply> void checkReply(T reply) {
        if (reply != null) {
            if (reply.isThrottle()) {
                throw new APIThrottleException();
            }
        }
    }

    /**
     * Internal method
     *
     * @param request  The request to get
     * @param callback The callback to execute
     */
    private Future<HttpResponse> get(Request request, Callback<?> callback) {
        return exService.submit(() -> httpClient.execute(new HttpGet(request.getURL(this)), buildResponseHandler(request, callback)));
    }

    private class SyncCallback<T extends AbstractReply> implements Callback<T> {
        
        private Throwable failCause;
        private T result;
        
        @Override
        public void callback(Throwable failCause, T result) {
            this.failCause = failCause;
            this.result = result;
        }
    }
}
