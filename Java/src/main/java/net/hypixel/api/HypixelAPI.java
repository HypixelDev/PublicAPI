package net.hypixel.api;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.mastfrog.netty.http.client.HttpClient;
import com.mastfrog.netty.http.client.ResponseFuture;
import com.mastfrog.netty.http.client.ResponseHandler;

import net.hypixel.api.reply.*;
import net.hypixel.api.util.APIUtil;
import net.hypixel.api.util.Callback;
import net.hypixel.api.util.HypixelAPIException;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@SuppressWarnings("unused")
public class HypixelAPI {
    private static final String BASE_URL = "https://api.hypixel.net/";
    private static HypixelAPI instance;
    private final Gson gson;
    private final ReentrantReadWriteLock lock;
    private final HttpClient httpClient;
    private UUID apiKey;
    private HypixelAPI() {
        gson = new Gson();
        lock = new ReentrantReadWriteLock();
        httpClient = new HttpClient();
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
        httpClient.shutdown();
        instance = null;
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
     * Call this method to get information about this API's key
     * This method is synchronous and will block the thread until the request is completed and returned.
     * It is preferred to use the asynchronous counterpart to this method.
     *
     * @see #getKeyInfo(net.hypixel.api.util.Callback) The asynchronous version of this method
     *
     * @return The KeyReply from the API
     * @throws net.hypixel.api.util.HypixelAPIException A wrapper for any exceptions that occur.
     */
    public KeyReply getKeyInfoSync() throws HypixelAPIException {
        return getKeyInfoSync(apiKey);
    }

    /**
     * Call this method to get information about this API's key
     * This method is asynchronous and is preferred over it's synchronous counterpart.
     *
     * @param callback The callback to execute when finished
     */
    public void getKeyInfo(Callback<KeyReply> callback) {
        getKeyInfo(apiKey, callback);
    }

    /**
     * Call this method to get information about the provided API key
     * This method is synchronous and will block the thread until the request is completed and returned.
     * It is preferred to use the asynchronous counterpart to this method.
     *
     * @see #getKeyInfo(java.util.UUID, net.hypixel.api.util.Callback) The asynchronous version of this method
     *
     * @param apiKey The key to get information about
     * @return The KeyReply from the API
     * @throws net.hypixel.api.util.HypixelAPIException A wrapper for any exceptions that occur.
     */
    public KeyReply getKeyInfoSync(UUID apiKey) throws HypixelAPIException {
        lock.readLock().lock();
        try {
            SyncCallback<KeyReply> callback = new SyncCallback<>(KeyReply.class);
            if (doKeyCheck(callback)) {
                try {
                    get(BASE_URL + "key?key=" + apiKey.toString(), callback).await();
                } catch (InterruptedException e) {
                    throw new HypixelAPIException(e);
                }
            }
            if(callback.failCause!=null) {
                throw new HypixelAPIException(callback.failCause);
            } else {
                return callback.result;
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Call this method to get information about the provided API key
     * This method is asynchronous and is preferred over it's synchronous counterpart.
     *
     * @param apiKey   The key to get information about
     * @param callback The callback to execute when finished
     */
    public void getKeyInfo(UUID apiKey, Callback<KeyReply> callback) {
        lock.readLock().lock();
        try {
            if (doKeyCheck(callback)) {
                get(BASE_URL + "key?key=" + apiKey.toString(), callback);
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Call this method to find a guild's ID
     * This method is synchronous and will block the thread until the request is completed and returned.
     * It is preferred to use the asynchronous counterpart to this method.
     *
     * @see #findGuild(String, String, net.hypixel.api.util.Callback) The asynchronous version of this method
     *
     * @param name The name of the guild, optional
     * @param player A player in the guild, optional
     * @return The FindGuildReply from the API
     * @throws net.hypixel.api.util.HypixelAPIException A wrapper for any exceptions that occur.
     */
    public FindGuildReply findGuildSync(String name, String player) throws HypixelAPIException {
        lock.readLock().lock();
        try {
            SyncCallback<FindGuildReply> callback = new SyncCallback<>(FindGuildReply.class);
            if (doKeyCheck(callback)) {
                String args;
                if (name != null) {
                    args = "byName=" + StringEscapeUtils.escapeHtml4(name);
                } else if (player != null) {
                    args = "byPlayer=" + StringEscapeUtils.escapeHtml4(player);
                } else {
                    throw new HypixelAPIException("Neither name nor player was provided!");
                }
                try {
                    get(BASE_URL + "findGuild?key=" + apiKey.toString() + "&" + args, callback).await();
                } catch (InterruptedException e) {
                    throw new HypixelAPIException(e);
                }
            }
            if(callback.failCause!=null) {
                throw new HypixelAPIException(callback.failCause);
            } else {
                return callback.result;
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Call this method to find a guild's ID
     * This method is asynchronous and is preferred over it's synchronous counterpart.
     *
     * @param name     The name of the guild, optional
     * @param player   A player in the guild, optional
     * @param callback The callback to execute when finished
     */
    public void findGuild(String name, String player, Callback<FindGuildReply> callback) {
        lock.readLock().lock();
        try {
            if (doKeyCheck(callback)) {
                String args;
                if (name != null) {
                    args = "byName=" + StringEscapeUtils.escapeHtml4(name);
                } else if (player != null) {
                    args = "byPlayer=" + StringEscapeUtils.escapeHtml4(player);
                } else {
                    callback.callback(new HypixelAPIException("Neither name nor player was provided!"), null);
                    return;
                }
                get(BASE_URL + "findGuild?key=" + apiKey.toString() + "&" + args, callback);
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Call this method to get a guild's object
     * This method is synchronous and will block the thread until the request is completed and returned.
     * It is preferred to use the asynchronous counterpart to this method.
     *
     * @see #getGuild(String, net.hypixel.api.util.Callback) The asynchronous version of this method
     *
     * @param id The ID of the guild
     * @return The GuildReply from the API
     * @throws HypixelAPIException A wrapper for any exceptions that occur.
     */
    public GuildReply getGuildSync(String id) throws HypixelAPIException {
        lock.readLock().lock();
        try {
            SyncCallback<GuildReply> callback = new SyncCallback<>(GuildReply.class);
            if (doKeyCheck(callback)) {
                if (id == null) {
                    callback.callback(new HypixelAPIException("Guild id wasn't provided!"), null);
                } else {
                    try {
                        get(BASE_URL + "guild?key=" + apiKey.toString() + "&id=" + StringEscapeUtils.escapeHtml4(id), callback).await();
                    } catch (InterruptedException e) {
                        throw new HypixelAPIException(e);
                    }
                }
            }
            if(callback.failCause!=null) {
                throw new HypixelAPIException(callback.failCause);
            } else {
                return callback.result;
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Call this method to get a guild's object
     * This method is asynchronous and is preferred over it's synchronous counterpart.
     *
     * @param id       The ID of the guild
     * @param callback The callback to execute when finished
     */
    public void getGuild(String id, Callback<GuildReply> callback) {
        lock.readLock().lock();
        try {
            if (doKeyCheck(callback)) {
                if (id == null) {
                    callback.callback(new HypixelAPIException("Guild id wasn't provided!"), null);
                } else {
                    get(BASE_URL + "guild?key=" + apiKey.toString() + "&id=" + StringEscapeUtils.escapeHtml4(id), callback);
                }
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Call this method to get the active boosters
     * This method is synchronous and will block the thread until the request is completed and returned.
     * It is preferred to use the asynchronous counterpart to this method.
     *
     * @see #getBoosters(net.hypixel.api.util.Callback) The asynchronous version of this method
     *
     * @return The BoostersReply from the API
     * @throws HypixelAPIException A wrapper for any exceptions that occur.
     */
    public BoostersReply getBoostersSync() throws HypixelAPIException {
        lock.readLock().lock();
        try {
            SyncCallback<BoostersReply> callback = new SyncCallback<>(BoostersReply.class);
            if (doKeyCheck(callback)) {
                try {
                    get(BASE_URL + "boosters?key=" + apiKey.toString(), callback).await();
                } catch (InterruptedException e) {
                    throw new HypixelAPIException(e);
                }
            }
            if(callback.failCause!=null) {
                throw new HypixelAPIException(callback.failCause);
            } else {
                return callback.result;
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Call this method to get the active boosters
     * This method is asynchronous and is preferred over it's synchronous counterpart.
     *
     * @param callback The callback to execute when finished
     */
    public void getBoosters(Callback<BoostersReply> callback) {
        lock.readLock().lock();
        try {
            if (doKeyCheck(callback)) {
                get(BASE_URL + "boosters?key=" + apiKey.toString(), callback);
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Call this method to get a player's friends
     * This method is synchronous and will block the thread until the request is completed and returned.
     * It is preferred to use the asynchronous counterpart to this method.
     *
     * @see #getFriends(String, net.hypixel.api.util.Callback) The asynchronous version of this method
     *
     * @param player The player to find friends of
     * @return The FriendsReply from the API
     * @throws HypixelAPIException A wrapper for any exceptions that occur.
     */
    public FriendsReply getFriendsSync(String player) throws HypixelAPIException {
        lock.readLock().lock();
        try {
            SyncCallback<FriendsReply> callback = new SyncCallback<>(FriendsReply.class);
            if (doKeyCheck(callback)) {
                if (player == null) {
                    callback.callback(new HypixelAPIException("No player was provided!"), null);
                } else {
                    try {
                        get(BASE_URL + "friends?key=" + apiKey.toString() + "&player=" + StringEscapeUtils.escapeHtml4(player), callback).await();
                    } catch (InterruptedException e) {
                        throw new HypixelAPIException(e);
                    }
                }
            }
            if(callback.failCause!=null) {
                throw new HypixelAPIException(callback.failCause);
            } else {
                return callback.result;
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Call this method to get a player's friends
     * This method is asynchronous and is preferred over it's synchronous counterpart.
     *
     * @param player   The player to find friends of
     * @param callback The callback to execute when finished
     */
    public void getFriends(String player, Callback<FriendsReply> callback) {
        lock.readLock().lock();
        try {
            if (doKeyCheck(callback)) {
                if (player == null) {
                    callback.callback(new HypixelAPIException("No player was provided!"), null);
                } else {
                    get(BASE_URL + "friends?key=" + apiKey.toString() + "&player=" + StringEscapeUtils.escapeHtml4(player), callback);
                }
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Call this method to get a player's friends by its uuid
     * This method is asynchronous and is preferred over it's synchronous counterpart.
     *
     * @param uuid   The uuid of the player to find friends of
     * @param callback The callback to execute when finished
     */
    public void getFriendsByUUID(String uuid, Callback<FriendsReply> callback) {
        lock.readLock().lock();
        try {
            if (doKeyCheck(callback)) {
                if (uuid == null) {
                    callback.callback(new HypixelAPIException("No player was provided!"), null);
                } else {
                    get(BASE_URL + "friends?key=" + apiKey.toString() + "&uuid=" + StringEscapeUtils.escapeHtml4(uuid), callback);
                }
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Call this method to get a player's session
     * This method is synchronous and will block the thread until the request is completed and returned.
     * It is preferred to use the asynchronous counterpart to this method.
     *
     * @see #getSession(String, net.hypixel.api.util.Callback)
     *
     * @param player The player to get the session of
     * @return The SessionReply from the API
     */
    public SessionReply getSessionSync(String player) {
        lock.readLock().lock();
        try {
            SyncCallback<SessionReply> callback = new SyncCallback<>(SessionReply.class);
            if (doKeyCheck(callback)) {
                if (player == null) {
                    throw new HypixelAPIException("No player was provided!");
                } else {
                    try {
                        get(BASE_URL + "session?key=" + apiKey.toString() + "&player=" + StringEscapeUtils.escapeHtml4(player), callback).await();
                    } catch (InterruptedException e) {
                        throw new HypixelAPIException(e);
                    }
                }
            }
            if (callback.failCause != null) {
                throw new HypixelAPIException(callback.failCause);
            } else {
                return callback.result;
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Call this method to get a player's session
     * This method is asynchronous and is preferred over it's synchronous counterpart.
     *
     * @param player   The player to get the session of
     * @param callback The callback to execute when finished
     */
    public void getSession(String player, Callback<SessionReply> callback) {
        lock.readLock().lock();
        try {
            if (doKeyCheck(callback)) {
                if (player == null) {
                    callback.callback(new HypixelAPIException("No player was provided!"), null);
                } else {
                    get(BASE_URL + "session?key=" + apiKey.toString() + "&player=" + StringEscapeUtils.escapeHtml4(player), callback);
                }
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Call this method to get a player's object
     * This method is synchronous and will block the thread until the request is completed and returned.
     * It is preferred to use the asynchronous counterpart to this method.
     *
     * @see #getPlayer(String, java.util.UUID, net.hypixel.api.util.Callback) The asynchronous version of this method
     *
     * @param player The name of the player, optional
     * @param uuid The uuid of the player, optional
     * @return The PlayerReply from the API
     * @throws HypixelAPIException A wrapper for any exceptions that occur.
     */
    public PlayerReply getPlayerSync(String player, UUID uuid) throws HypixelAPIException {
        lock.readLock().lock();
        try {
            SyncCallback<PlayerReply> callback = new SyncCallback<>(PlayerReply.class);
            if (doKeyCheck(callback)) {
                String args;
                if (player != null) {
                    args = "name=" + StringEscapeUtils.escapeHtml4(player);
                } else if (uuid != null) {
                    args = "uuid=" + APIUtil.stripDashes(uuid);
                } else {
                    throw new HypixelAPIException("Neither player nor uuid was provided!");
                }
                try {
                    get(BASE_URL + "player?key=" + apiKey.toString() + "&" + args, callback).await();
                } catch (InterruptedException e) {
                    throw new HypixelAPIException(e);
                }
            }
            if(callback.failCause!=null) {
                throw new HypixelAPIException(callback.failCause);
            } else {
                return callback.result;
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Call this method to get a player's object
     * This method is asynchronous and is preferred over it's synchronous counterpart.
     *
     * @param player   The name of the player, optional
     * @param uuid     The uuid of the player, optional
     * @param callback The callback to execute when finished
     */
    public void getPlayer(String player, UUID uuid, Callback<PlayerReply> callback) {
        lock.readLock().lock();
        try {
            if (doKeyCheck(callback)) {
                String args;
                if (player != null) {
                    args = "name=" + StringEscapeUtils.escapeHtml4(player);
                } else if (uuid != null) {
                    args = "uuid=" + APIUtil.stripDashes(uuid);
                } else {
                    callback.callback(new HypixelAPIException("Neither player nor uuid was provided!"), null);
                    return;
                }
                get(BASE_URL + "player?key=" + apiKey.toString() + "&" + args, callback);
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
     * @param callback The callback to execute
     * @param <T> The class of the callback
     * @return The ResponseHandler that wraps the callback
     */
    private <T extends AbstractReply> ResponseHandler<String> buildResponseHandler(final Callback<T> callback) {
        return new ResponseHandler<String>(String.class) {
            @Override
            protected void receive(String obj) {
                T value;
                try {
                    value = gson.fromJson(obj, callback.getClazz());
                } catch (Throwable t) {
                    callback.callback(t, null);
                    return;
                }
                callback.callback(null, value);
            }

            @Override
            protected void onError(Throwable err) {
                callback.callback(err, null);
            }
        };
    }

    /**
     * Internal method
     *
     * @param url The URL to send the request to
     * @param callback The callback to execute
     */
    private ResponseFuture get(String url, Callback<?> callback) {
        return httpClient.get().setURL(url).execute(buildResponseHandler(callback));
    }

    private class SyncCallback<T extends AbstractReply> extends Callback<T> {
        private Throwable failCause;
        private T result;

        private SyncCallback(Class<T> clazz) {
            super(clazz);
        }

        @Override
        public void callback(Throwable failCause, T result) {
            this.failCause = failCause;
            this.result = result;
        }
    }
}
