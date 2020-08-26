package net.hypixel.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.hypixel.api.adapters.BoostersTypeAdapterFactory;
import net.hypixel.api.adapters.DateTimeTypeAdapter;
import net.hypixel.api.adapters.GameTypeTypeAdapter;
import net.hypixel.api.adapters.UUIDTypeAdapter;
import net.hypixel.api.exceptions.APIThrottleException;
import net.hypixel.api.exceptions.HypixelAPIException;
import net.hypixel.api.reply.*;
import net.hypixel.api.reply.skyblock.*;
import net.hypixel.api.util.GameType;
import net.hypixel.api.util.ResourceType;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class HypixelAPI {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(UUID.class, new UUIDTypeAdapter())
            .registerTypeAdapter(GameType.class, new GameTypeTypeAdapter())
            .registerTypeAdapter(ZonedDateTime.class, new DateTimeTypeAdapter())

            .registerTypeAdapterFactory(new BoostersTypeAdapterFactory<>(BoostersReply.Booster.class))

            .create();
    private static final String BASE_URL = "https://api.hypixel.net/";

    private final UUID apiKey;

    private final ExecutorService executorService;
    private final HttpClient httpClient;

    public HypixelAPI(UUID apiKey) {
        this.apiKey = apiKey;

        this.executorService = Executors.newCachedThreadPool();
        this.httpClient = HttpClientBuilder.create().build();
    }

    /**
     * Shuts down the internal executor service
     */
    public void shutdown() {
        executorService.shutdown();
    }

    /**
     * @return currently set API key
     */
    public UUID getApiKey() {
        return apiKey;
    }

    public CompletableFuture<BoostersReply> getBoosters() {
        return get(BoostersReply.class, "boosters");
    }

    public CompletableFuture<LeaderboardsReply> getLeaderboards() {
        return get(LeaderboardsReply.class, "leaderboards");
    }

    public CompletableFuture<WatchdogStatsReply> getWatchdogStats() {
        return get(WatchdogStatsReply.class, "watchdogStats");
    }

    /**
     * This is now included inside {@link HypixelAPI#getGameCounts()}
     */
    @Deprecated
    public CompletableFuture<PlayerCountReply> getPlayerCount() {
        return get(PlayerCountReply.class, "playerCount");
    }

    public CompletableFuture<PlayerReply> getPlayerByUuid(UUID player) {
        return get(PlayerReply.class, "player", "uuid", player);
    }

    /**
     * @param player uuid of a player in string format, can be both dashed or undashed.
     * @return the future
     */
    public CompletableFuture<PlayerReply> getPlayerByUuid(String player) {
        return get(PlayerReply.class, "player", "uuid", player);
    }

    @Deprecated
    public CompletableFuture<PlayerReply> getPlayerByName(String player) {
        return get(PlayerReply.class, "player", "name", player);
    }

    public CompletableFuture<FriendsReply> getFriends(UUID player) {
        return get(FriendsReply.class, "friends", "uuid", player);
    }

    /**
     * @param player uuid of a player in string format, can be both dashed or undashed.
     * @return the future
     */
    public CompletableFuture<FriendsReply> getFriends(String player) {
        return get(FriendsReply.class, "friends", "uuid", player);
    }

    public CompletableFuture<GuildReply> getGuildByPlayer(UUID player) {
        return get(GuildReply.class, "guild", "player", player);
    }

    /**
     * @param player uuid of a player in string format, can be both dashed or undashed.
     * @return the future
     */
    public CompletableFuture<GuildReply> getGuildByPlayer(String player) {
        return get(GuildReply.class, "guild", "player", player);
    }

    public CompletableFuture<GuildReply> getGuildByName(String name) {
        return get(GuildReply.class, "guild", "name", name);
    }

    /**
     * @param id mongo id hex string
     * @return the future
     */
    public CompletableFuture<GuildReply> getGuildById(String id) {
        return get(GuildReply.class, "guild", "id", id);
    }

    /**
     * You can directly get the guild using {@link HypixelAPI#getGuildByPlayer(UUID)}
     */
    @Deprecated
    public CompletableFuture<FindGuildReply> findGuildByPlayer(UUID player) {
        return get(FindGuildReply.class, "findGuild", "byUuid", player);
    }

    /**
     * You can directly get the guild using {@link HypixelAPI#getGuildByPlayer(String)}
     */
    @Deprecated
    public CompletableFuture<FindGuildReply> findGuildByPlayer(String player) {
        return get(FindGuildReply.class, "findGuild", "byUuid", player);
    }

    /**
     * You can directly get the guild using {@link HypixelAPI#getGuildByName(String)})}
     */
    @Deprecated
    public CompletableFuture<GuildReply> findGuildByName(String name) {
        return get(GuildReply.class, "findGuild", "byName", name);
    }

    public CompletableFuture<KeyReply> getKey() {
        return get(KeyReply.class, "key");
    }

    public CompletableFuture<GameCountsReply> getGameCounts() {
        return get(GameCountsReply.class, "gameCounts");
    }

    public CompletableFuture<SkyBlockProfileReply> getSkyBlockProfile(String profile) {
        return get(SkyBlockProfileReply.class, "skyblock/profile", "profile", profile);
    }

    public CompletableFuture<SkyBlockNewsReply> getSkyBlockNews() {
        return get(SkyBlockNewsReply.class, "skyblock/news");
    }

    public CompletableFuture<SkyBlockAuctionsReply> getSkyBlockAuctions(int page) {
        return get(SkyBlockAuctionsReply.class, "skyblock/auctions", "page", page);
    }

    /**
     * Gets the current status of the player with information about the server they are in
     * at that moment.
     * In case the person is in limbo, result will be the last known server
     *
     * @param uuid of player
     * @return CompletableFuture with status reply
     */
    public CompletableFuture<StatusReply> getStatus(UUID uuid) {
        return get(StatusReply.class, "status", "uuid", uuid);
    }

    /**
     * Gets up to 100 of the player's most recently played games. Games are removed from this list after 3 days.
     * @param uuid of player
     * @return CompletableFuture with recentGames reply
     */
    public CompletableFuture<RecentGamesReply> getRecentGames(UUID uuid) {
        return get(RecentGamesReply.class, "recentGames", "uuid", uuid);
    }

    /**
     * Retrieve resources which don't change often.
     *
     * @param resource to be requested
     * @return CompletableFuture with resource reply
     */
    public CompletableFuture<ResourceReply> getResource(ResourceType resource) {
        return getResource(resource.getPath());
    }

    /**
     * Requests information about products in bazaar.
     *
     * @return CompletableFuture with BazaarReply
     */
    public CompletableFuture<BazaarReply> getBazaar() {
        return get(BazaarReply.class, "skyblock/bazaar");
    }

    /**
     * Retrieve resources which don't change often.
     *
     * @param resource to be requested
     * @return CompletableFuture with resource reply
     */
    public CompletableFuture<ResourceReply> getResource(String resource) {
        return get("resources/" + resource)
                .thenApply(content -> new ResourceReply(GSON.fromJson(content, JsonObject.class)));
    }

    /**
     * Get the response content from the specified path with the specified query string parameters
     *
     * @param path the path to be requested
     * @param params the parameters for query string
     * @return CompletableFuture with the content of the response
     */
    private CompletableFuture<String> get(String path, Map<String, Object> params) {
        CompletableFuture<String> future = new CompletableFuture<>();
        try {
            StringBuilder url = new StringBuilder(BASE_URL);

            url.append(path);

            String queryString = params.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining("&", "?", ""));

            url.append(queryString);

            executorService.submit(() -> {
                try {
                    String response = httpClient.execute(
                            new HttpGet(url.toString()),
                            obj -> EntityUtils.toString(obj.getEntity(), "UTF-8")
                    );

                    future.complete(response);
                } catch (Throwable t) {
                    future.completeExceptionally(t);
                }
            });
        } catch (Throwable throwable) {
            future.completeExceptionally(throwable);
        }
        return future;
    }

    /**
     * Get the response content from the specified path
     *
     * @param path the path to be requested
     * @return CompletableFuture with the content of the response
     */
    private CompletableFuture<String> get(String path) {
        return get(path, Collections.emptyMap());
    }

    /**
     * Get a Reply of type R from the specified path with the specified query string parameters
     *
     * @param clazz the reply expected class type
     * @param path the path to be requested
     * @param params the parameters for query string
     * @param <R> the type of the reply
     * @return CompletableFuture with the reply
     */
    private <R extends AbstractReply> CompletableFuture<R> get(Class<R> clazz, String path, Object... params) {
        if (params.length % 2 != 0)
            throw new IllegalArgumentException("Need both key and value for parameters");

        Map<String, Object> mapParams = new HashMap<>();

        mapParams.put("key", apiKey);

        for (int i = 0; i < params.length - 1; i += 2) {
            mapParams.put(params[i].toString(), params[i + 1]);
        }

        return get(clazz, path, mapParams);
    }

    /**
     * Get a Reply of type R from the specified path with the specified query string parameters
     *
     * @param clazz the reply expected class type
     * @param path the path to be requested
     * @param params the parameters for query string
     * @param <R> the type of the reply
     * @return CompletableFuture with the reply
     */
    private <R extends AbstractReply> CompletableFuture<R> get(Class<R> clazz, String path, Map<String, Object> params) {
        return get(path, params)
                .thenApply(content -> {
                    R response = GSON.fromJson(content, clazz);
                    checkReply(response);
                    return response;
                });
    }

    /**
     * Checks reply and throws appropriate exceptions based on it's content
     *
     * @param reply The reply to check
     * @param <T>   The class of the reply
     */
    private <T extends AbstractReply> void checkReply(T reply) {
        if (reply != null) {
            if (reply.isThrottle()) {
                throw new APIThrottleException();
            } else if (!reply.isSuccess()) {
                throw new HypixelAPIException(reply.getCause());
            }
        }
    }
}
