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
import net.hypixel.api.http.HTTPQueryParams;
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

    /**
     * @param apiKey     the Hypixel API key to be used for the HTTP requests
     * @param httpClient a {@link HypixelHTTPClient} that implements the HTTP behaviour for communicating with the API
     */
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
     * @return the currently set API key
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

    /**
     * @param player uuid of a player
     * @return {@link CompletableFuture} containing {@link PlayerReply}
     */
    public CompletableFuture<PlayerReply> getPlayerByUuid(UUID player) {
        return get(PlayerReply.class, "player",
                HTTPQueryParams.create()
                        .add("uuid", player)
        );
    }

    /**
     * @param player uuid of a player in string format, can be both dashed or undashed.
     * @return {@link CompletableFuture} containing {@link PlayerReply}
     */
    public CompletableFuture<PlayerReply> getPlayerByUuid(String player) {
        return get(PlayerReply.class, "player",
                HTTPQueryParams.create()
                        .add("uuid", player)
        );
    }

    /**
     * @param player the minecraft username of the player.
     * @return {@link CompletableFuture} containing {@link PlayerReply}
     * @deprecated While this method should continue functioning we recommend using the Mojang API for requesting UUID's by username.
     * See issue <a href="https://github.com/HypixelDev/PublicAPI/issues/249#issuecomment-645634722">#249</a>
     * This endpoint is also subject to limiting requests of the same username in a short period of time.
     */
    @Deprecated
    public CompletableFuture<PlayerReply> getPlayerByName(String player) {
        return get(PlayerReply.class, "player",
                HTTPQueryParams.create()
                        .add("name", player)
        );
    }

    public CompletableFuture<FriendsReply> getFriends(UUID player) {
        return get(FriendsReply.class, "friends",
                HTTPQueryParams.create()
                        .add("uuid", player)
        );
    }

    /**
     * @param player uuid of a player in string format, can be both dashed or undashed
     * @return {@link CompletableFuture} containing {@link FriendsReply}
     */
    public CompletableFuture<FriendsReply> getFriends(String player) {
        return get(FriendsReply.class, "friends",
                HTTPQueryParams.create()
                        .add("uuid", player)
        );
    }

    /**
     * @param player uuid of a player
     * @return {@link CompletableFuture} containing {@link GuildReply}
     */
    public CompletableFuture<GuildReply> getGuildByPlayer(UUID player) {
        return get(GuildReply.class, "guild",
                HTTPQueryParams.create()
                        .add("player", player)
        );
    }

    /**
     * @param player uuid of a player in string format, can be both dashed or undashed
     * @return {@link CompletableFuture} containing {@link GuildReply}
     */
    public CompletableFuture<GuildReply> getGuildByPlayer(String player) {
        return get(GuildReply.class, "guild",
                HTTPQueryParams.create()
                        .add("player", player)
        );
    }

    /**
     * @param name the name of the guild
     * @return {@link CompletableFuture} containing {@link GuildReply}
     */
    public CompletableFuture<GuildReply> getGuildByName(String name) {
        return get(GuildReply.class, "guild",
                HTTPQueryParams.create()
                        .add("name", name)
        );
    }

    /**
     * @param id mongo id hex string
     * @return {@link CompletableFuture} containing {@link GuildReply}
     */
    public CompletableFuture<GuildReply> getGuildById(String id) {
        return get(GuildReply.class, "guild",
                HTTPQueryParams.create()
                        .add("id", id)
        );
    }

    public CompletableFuture<KeyReply> getKey() {
        return get(KeyReply.class, "key");
    }

    public CompletableFuture<CountsReply> getCounts() {
        return get(CountsReply.class, "counts");
    }

    /**
     * Gets the current status of the player with information about the server they are in
     * at that moment.
     * In case the person is in limbo, result will be the last known server
     *
     * @param uuid of player
     * @return {@link CompletableFuture} containing {@link StatusReply}
     */
    public CompletableFuture<StatusReply> getStatus(UUID uuid) {
        return get(StatusReply.class, "status",
                HTTPQueryParams.create()
                        .add("uuid", uuid)
        );
    }

    /**
     * Gets up to 100 of the player's most recently played games. Games are removed from this list after 3 days.
     *
     * @param uuid of player
     * @return {@link CompletableFuture} containing {@link RecentGamesReply}
     */
    public CompletableFuture<RecentGamesReply> getRecentGames(UUID uuid) {
        return get(RecentGamesReply.class, "recentGames",
                HTTPQueryParams.create()
                        .add("uuid", uuid)
        );
    }

    /**
     * Retrieve resources which don't change often.
     *
     * @param resource to be requested
     * @return {@link CompletableFuture} containing {@link ResourceReply}
     */
    public CompletableFuture<ResourceReply> getResource(ResourceType resource) {
        return getResource(resource.getPath());
    }

    public CompletableFuture<ResourceReply> getResource(String resource) {
        return requestResource(resource);
    }

    public CompletableFuture<SkyBlockProfileReply> getSkyBlockProfile(String profile) {
        return get(SkyBlockProfileReply.class, "skyblock/profile",
                HTTPQueryParams.create()
                        .add("profile", profile)
        );
    }

    public CompletableFuture<SkyBlockNewsReply> getSkyBlockNews() {
        return get(SkyBlockNewsReply.class, "skyblock/news");
    }

    public CompletableFuture<SkyBlockAuctionsReply> getSkyBlockAuctions(int page) {
        return get(SkyBlockAuctionsReply.class, "skyblock/auctions",
                HTTPQueryParams.create()
                        .add("page", page)
        );
    }

    /**
     * Requests information about products in bazaar.
     *
     * @return {@link CompletableFuture} containing {@link SkyBlockBazaarReply}
     */
    public CompletableFuture<SkyBlockBazaarReply> getSkyBlockBazaar() {
        return get(SkyBlockBazaarReply.class, "skyblock/bazaar");
    }

    private <R extends AbstractReply> CompletableFuture<R> get(Class<R> clazz, String request) {
        return get(clazz, request, null);
    }

    private <R extends AbstractReply> CompletableFuture<R> get(Class<R> clazz, String request, HTTPQueryParams params) {
        String url = BASE_URL + request;
        if (params != null) {
            url = params.getAsQueryString(url);
        }
        return httpClient.makeAuthenticatedRequest(url, apiKey).thenApply(content -> {
            if (clazz == ResourceReply.class) {
                return checkReply((R) new ResourceReply(GSON.fromJson(content, JsonObject.class)));
            } else {
                return checkReply(GSON.fromJson(content, clazz));
            }
        });
    }

    private CompletableFuture<ResourceReply> requestResource(String resource) {
        return httpClient.makeRequest(BASE_URL + "resources/" + resource)
                .thenApply(content -> checkReply(new ResourceReply(GSON.fromJson(content, JsonObject.class))));
    }

    /**
     * Checks reply and throws appropriate exceptions based on it's content
     *
     * @param reply The reply to check
     * @param <T>   The class of the reply
     * @return the same object that was provided for cleaner usage
     */
    private <T extends AbstractReply> T checkReply(T reply) {
        if (reply != null) {
            if (reply.isThrottle()) {
                throw new APIThrottleException();
            } else if (!reply.isSuccess()) {
                throw new HypixelAPIException(reply.getCause());
            }
        }
        return reply;
    }
}
