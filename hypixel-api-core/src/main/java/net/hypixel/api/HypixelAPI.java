package net.hypixel.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.hypixel.api.exceptions.BadResponseException;
import net.hypixel.api.exceptions.BadStatusCodeException;
import net.hypixel.api.http.HTTPQueryParams;
import net.hypixel.api.http.HypixelHttpClient;
import net.hypixel.api.http.HypixelHttpResponse;
import net.hypixel.api.pets.IPetRepository;
import net.hypixel.api.pets.impl.PetRepositoryImpl;
import net.hypixel.api.reply.*;
import net.hypixel.api.reply.skyblock.*;
import net.hypixel.api.reply.skyblock.bingo.SkyBlockBingoDataReply;
import net.hypixel.api.reply.skyblock.firesales.SkyBlockFireSalesReply;
import net.hypixel.api.util.PropertyFilter;
import net.hypixel.api.util.ResourceType;
import net.hypixel.api.util.Utilities;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class HypixelAPI {
    static final String BASE_URL = "https://api.hypixel.net/v2/";

    private final HypixelHttpClient httpClient;

    /**
     * @param httpClient a {@link HypixelHttpClient} that implements the HTTP behaviour for communicating with the API
     */
    public HypixelAPI(HypixelHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Shuts down the {@link HypixelHttpClient}
     */
    public void shutdown() {
        httpClient.shutdown();
    }

    public CompletableFuture<BoostersReply> getBoosters() {
        return get(true, BoostersReply.class, "boosters");
    }

    public CompletableFuture<LeaderboardsReply> getLeaderboards() {
        return get(true, LeaderboardsReply.class, "leaderboards");
    }

    public CompletableFuture<PunishmentStatsReply> getPunishmentStats() {
        return get(true, PunishmentStatsReply.class, "punishmentstats");
    }

    /**
     * @param player uuid of a player
     * @return {@link CompletableFuture} containing {@link PlayerReply}
     */
    public CompletableFuture<PlayerReply> getPlayerByUuid(UUID player) {
        return get(true, PlayerReply.class, "player",
                HTTPQueryParams.create()
                        .add("uuid", player)
        );
    }

    /**
     * @param player uuid of a player in string format, can be both dashed or undashed.
     * @return {@link CompletableFuture} containing {@link PlayerReply}
     */
    public CompletableFuture<PlayerReply> getPlayerByUuid(String player) {
        return get(true, PlayerReply.class, "player",
                HTTPQueryParams.create()
                        .add("uuid", player)
        );
    }

    /**
     * Same as {@link #getPlayerByUuid(UUID)}, but the resulting player object will only contain
     * properties explicitly included via a {@link PropertyFilter filter}.
     */
    public CompletableFuture<PlayerReply> getPlayerByUuid(UUID player, PropertyFilter filter) {
        return applyFilterFuture(getPlayerByUuid(player), filter);
    }

    /**
     * Same as {@link #getPlayerByUuid(String)}, but the resulting player object will only contain
     * properties explicitly included via a {@link PropertyFilter filter}.
     */
    public CompletableFuture<PlayerReply> getPlayerByUuid(String player, PropertyFilter filter) {
        return applyFilterFuture(getPlayerByUuid(player), filter);
    }

    /**
     * Same as {@link #getPlayerByName(String)}, but the resulting player object will only contain
     * properties explicitly included via a {@link PropertyFilter filter}.
     */
    @Deprecated
    public CompletableFuture<PlayerReply> getPlayerByName(String player, PropertyFilter filter) {
        return applyFilterFuture(getPlayerByName(player), filter);
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
        return get(true, PlayerReply.class, "player",
                HTTPQueryParams.create()
                        .add("name", player)
        );
    }

    /**
     * @param player uuid of a player
     * @return {@link CompletableFuture} containing {@link GuildReply}
     */
    public CompletableFuture<GuildReply> getGuildByPlayer(UUID player) {
        return get(true, GuildReply.class, "guild",
                HTTPQueryParams.create()
                        .add("player", player)
        );
    }

    /**
     * @param player uuid of a player in string format, can be both dashed or undashed
     * @return {@link CompletableFuture} containing {@link GuildReply}
     */
    public CompletableFuture<GuildReply> getGuildByPlayer(String player) {
        return get(true, GuildReply.class, "guild",
                HTTPQueryParams.create()
                        .add("player", player)
        );
    }

    /**
     * @param name the name of the guild
     * @return {@link CompletableFuture} containing {@link GuildReply}
     */
    public CompletableFuture<GuildReply> getGuildByName(String name) {
        return get(true, GuildReply.class, "guild",
                HTTPQueryParams.create()
                        .add("name", name)
        );
    }

    /**
     * @param id mongo id hex string
     * @return {@link CompletableFuture} containing {@link GuildReply}
     */
    public CompletableFuture<GuildReply> getGuildById(String id) {
        return get(true, GuildReply.class, "guild",
                HTTPQueryParams.create()
                        .add("id", id)
        );
    }

    public CompletableFuture<CountsReply> getCounts() {
        return get(true, CountsReply.class, "counts");
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
        return get(true, StatusReply.class, "status",
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
        return get(true, RecentGamesReply.class, "recentGames",
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

    public CompletableFuture<IPetRepository> getPetRepository() {
        return getResource(ResourceType.VANITY_PETS)
                .thenApply(PetRepositoryImpl::new);
    }

    public CompletableFuture<SkyBlockProfileReply> getSkyBlockProfile(String profile) {
        return get(true, SkyBlockProfileReply.class, "skyblock/profile",
                HTTPQueryParams.create()
                        .add("profile", profile)
        );
    }

    /**
     * @param player uuid of a player.
     * @return the future
     */
    public CompletableFuture<SkyBlockProfilesReply> getSkyBlockProfiles(UUID player) {
        return get(true, SkyBlockProfilesReply.class, "skyblock/profiles",
                HTTPQueryParams.create()
                        .add("uuid", player)
        );
    }

    /**
     * @param player uuid of a player in string format, can be both dashed or undashed.
     * @return the future
     */
    public CompletableFuture<SkyBlockProfilesReply> getSkyBlockProfiles(String player) {
        return get(true, SkyBlockProfilesReply.class, "skyblock/profiles",
                HTTPQueryParams.create()
                        .add("uuid", player)
        );
    }

    /**
     * Request the bingo data of a provided player. See <a href="https://api.hypixel.net/#tag/SkyBlock/paths/~1skyblock~1bingo/get">/skyblock/bingo</a>
     *
     * @param player uuid of a player.
     * @return CompletableFuture containing a {@link SkyBlockBingoDataReply}
     */
    public CompletableFuture<SkyBlockBingoDataReply> getSkyblockBingoData(UUID player) {
        return get(true, SkyBlockBingoDataReply.class, "skyblock/bingo",
                HTTPQueryParams.create()
                        .add("uuid", player)
        );
    }

    /**
     * Request the bingo data of a provided player. See <a href="https://api.hypixel.net/#tag/SkyBlock/paths/~1skyblock~1bingo/get">/skyblock/bingo</a>
     *
     * @param player uuid of a player in string format, can be both dashed or undashed.
     * @return CompletableFuture containing a {@link SkyBlockBingoDataReply}
     */
    public CompletableFuture<SkyBlockBingoDataReply> getSkyblockBingoData(String player) {
        return get(true, SkyBlockBingoDataReply.class, "skyblock/bingo",
                HTTPQueryParams.create()
                        .add("uuid", player)
        );
    }

    public CompletableFuture<SkyBlockNewsReply> getSkyBlockNews() {
        return get(true, SkyBlockNewsReply.class, "skyblock/news");
    }

    public CompletableFuture<SkyBlockAuctionsReply> getSkyBlockAuctions(int page) {
        return get(false, SkyBlockAuctionsReply.class, "skyblock/auctions",
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
        return get(false, SkyBlockBazaarReply.class, "skyblock/bazaar");
    }

    public CompletableFuture<SkyBlockFireSalesReply> getSkyBlockFireSales() {
        return get(false, SkyBlockFireSalesReply.class, "skyblock/firesales");
    }

    /**
     * Applies a {@code filter} to a player object when it is received in an API response.
     */
    private CompletableFuture<PlayerReply> applyFilterFuture(CompletableFuture<PlayerReply> future, PropertyFilter filter) {
        return future.thenApply(reply -> {
            reply.getPlayer().filter(filter);
            return reply;
        });
    }

    private <R extends AbstractReply> CompletableFuture<R> get(boolean authenticated, Class<R> clazz, String request) {
        return get(authenticated, clazz, request, null);
    }

    private <R extends AbstractReply> CompletableFuture<R> get(boolean authenticated, Class<R> clazz, String request, HTTPQueryParams params) {
        String url = BASE_URL + request;
        if (params != null) {
            url = params.getAsQueryString(url);
        }

        CompletableFuture<HypixelHttpResponse> future = authenticated ? httpClient.makeAuthenticatedRequest(url) : httpClient.makeRequest(url);
        return future
                .thenApply(this::checkResponse)
                .thenApply(response -> {
                    if (clazz == ResourceReply.class) {
                        return checkReply((R) new ResourceReply(Utilities.GSON.fromJson(response.getBody(), JsonObject.class)));
                    }

                    R reply = Utilities.GSON.fromJson(response.getBody(), clazz);
                    if (reply instanceof RateLimitedReply) {
                        ((RateLimitedReply) reply).setRateLimit(response.getRateLimit());
                    }

                    return checkReply(reply);
                });
    }

    private CompletableFuture<ResourceReply> requestResource(String resource) {
        return httpClient.makeRequest(BASE_URL + "resources/" + resource)
                .thenApply(this::checkResponse)
                .thenApply(response -> checkReply(new ResourceReply(Utilities.GSON.fromJson(response.getBody(), JsonObject.class))));
    }

    /**
     * Checks the status of the response and throws an exception if needed
     */
    private HypixelHttpResponse checkResponse(HypixelHttpResponse response) {
        if (response.getStatusCode() == 200) {
            return response;
        }

        String cause;
        try {
            cause = Utilities.GSON.fromJson(response.getBody(), JsonObject.class).get("cause").getAsString();
        } catch (JsonSyntaxException ignored) {
            cause = "Unknown (body is not json)";
        }

        throw new BadStatusCodeException(response.getStatusCode(), cause);
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
            if (!reply.isSuccess()) {
                throw new BadResponseException(reply.getCause());
            }
        }
        return reply;
    }
}
