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
import net.hypixel.api.http.HypixelHTTPClient;
import net.hypixel.api.reply.*;
import net.hypixel.api.reply.skyblock.*;
import net.hypixel.api.util.GameType;
import net.hypixel.api.util.ResourceType;

import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class HypixelAPI {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(UUID.class, new UUIDTypeAdapter())
            .registerTypeAdapter(GameType.class, new GameTypeTypeAdapter())
            .registerTypeAdapter(ZonedDateTime.class, new DateTimeTypeAdapter())
            .registerTypeAdapterFactory(new BoostersTypeAdapterFactory<>(BoostersReply.Booster.class))
            .create();
    private static final String BASE_URL = "https://api.hypixel.net/";

    private final UUID apiKey;
    private final HypixelHTTPClient httpClient;

    public HypixelAPI(UUID apiKey, HypixelHTTPClient httpClient) {
        this.apiKey = apiKey;
        this.httpClient = httpClient;
    }

    /**
     * Shuts down the {@link HypixelHTTPClient}
     */
    public void shutdown() {
        httpClient.shutdown();
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

    public CompletableFuture<PunishmentStatsReply> getPunishmentStats() {
        return get(PunishmentStatsReply.class, "punishmentstats");
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

    @Deprecated // TODO want this still?
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

    public CompletableFuture<KeyReply> getKey() {
        return get(KeyReply.class, "key");
    }

    public CompletableFuture<CountsReply> getCounts() {
        return get(CountsReply.class, "counts");
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
     *
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

    public CompletableFuture<ResourceReply> getResource(String resource) {
        return requestResource(resource);
    }

    /**
     * Execute Request asynchronously, executes Callback when finished
     *
     * @param request Request to get
     */
    // TODO use a map of string to object?
    private <R extends AbstractReply> CompletableFuture<R> get(Class<R> clazz, String request, Object... params) {
        if (params.length % 2 != 0) {
            throw new IllegalArgumentException("Need both key and value for parameters");
        }

        StringBuilder url = new StringBuilder(BASE_URL);
        url.append(request);
        boolean startedQuery = false;

        for (int i = 0; i < params.length - 1; i += 2) {
            if (!startedQuery) {
                startedQuery = true;
                url.append("?");
            } else {
                url.append("&");
            }

            url.append(params[i]).append("=").append(params[i + 1]);
        }

        return httpClient.makeAuthenticatedRequest(url.toString(), apiKey).thenApply(content -> {
            R response;
            if (clazz == ResourceReply.class) {
                response = (R) new ResourceReply(GSON.fromJson(content, JsonObject.class));
            } else {
                response = GSON.fromJson(content, clazz);
            }

            checkReply(response);

            return response;
        });
    }

    private CompletableFuture<ResourceReply> requestResource(String resource) {
        return httpClient.makeRequest(BASE_URL + "resources/" + resource).thenApply(content -> {
            ResourceReply reply = new ResourceReply(GSON.fromJson(content, JsonObject.class));
            checkReply(reply);
            return reply;
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
