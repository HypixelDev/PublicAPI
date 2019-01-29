package net.hypixel.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.hypixel.api.adapters.BoostersTypeAdapterFactory;
import net.hypixel.api.adapters.DateTimeTypeAdapter;
import net.hypixel.api.adapters.GameTypeTypeAdapter;
import net.hypixel.api.adapters.UUIDTypeAdapter;
import net.hypixel.api.reply.*;
import net.hypixel.api.util.GameType;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.String.format;
import static java.util.Collections.singletonMap;

public final class HypixelAPI {

  // Uses the initialization on demand holder idiom to lazily create a gson instance.
  private static final class GsonHolder {
    private static final Gson GSON =
        new GsonBuilder()
            .registerTypeAdapter(UUID.class, new UUIDTypeAdapter())
            .registerTypeAdapter(GameType.class, new GameTypeTypeAdapter())
            .registerTypeAdapter(ZonedDateTime.class, new DateTimeTypeAdapter())
            .registerTypeAdapterFactory(
                new BoostersTypeAdapterFactory<>(BoostersReply.Booster.class))
            .create();
  }

  private static final String SERVICE_URL_BASE = "https://api.hypixel.net";

  private final UUID apiKey;

  private final ExecutorService executor;
  private final HttpClient httpClient;

  public HypixelAPI(UUID apiKey) {
    this.apiKey = apiKey;
    this.executor = Executors.newCachedThreadPool();
    this.httpClient = HttpClientBuilder.create().build();
  }

  public void shutdown() {
    executor.shutdown();
  }

  /** @return The active ApiKey. */
  public UUID getApiKey() {
    return apiKey;
  }

  public CompletableFuture<BoostersReply> getBoosters() {
    return executeRequestAsync(BoostersReply.class, "boosters");
  }

  public CompletableFuture<LeaderboardsReply> getLeaderboards() {
    return executeRequestAsync(LeaderboardsReply.class, "leaderboards");
  }

  public CompletableFuture<WatchdogStatsReply> getWatchdogStats() {
    return executeRequestAsync(WatchdogStatsReply.class, "watchdogStats");
  }

  /** This is now included inside {@link HypixelAPI#getGameCounts()} */
  @Deprecated
  public CompletableFuture<PlayerCountReply> getPlayerCount() {
    return executeRequestAsync(PlayerCountReply.class, "playerCount");
  }

  /**
   * Session endpoint is bound to be removed at some point, data is mainly internal and highly
   * inaccurate for online checking
   */
  @Deprecated
  public CompletableFuture<SessionReply> getSessionByUuid(UUID player) {
    return executeRequestAsync(SessionReply.class, "session", singletonMap("uuid", player));
  }

  /**
   * Session endpoint is bound to be removed at some point, data is mainly internal and highly
   * inaccurate for online checking
   */
  @Deprecated
  public CompletableFuture<SessionReply> getSessionByUuid(String player) {
    return executeRequestAsync(SessionReply.class, "session", singletonMap("uuid", player));
  }

  public CompletableFuture<PlayerReply> getPlayerByUuid(UUID player) {
    return executeRequestAsync(PlayerReply.class, "player", singletonMap("uuid", player));
  }

  /**
   * @param player uuid of a player in string format, can be both dashed or undashed.
   * @return the future
   */
  public CompletableFuture<PlayerReply> getPlayerByUuid(String player) {
    return executeRequestAsync(PlayerReply.class, "player", singletonMap("uuid", player));
  }

  @Deprecated
  public CompletableFuture<PlayerReply> getPlayerByName(String player) {
    return executeRequestAsync(PlayerReply.class, "player", singletonMap("name", player));
  }

  public CompletableFuture<FriendsReply> getFriends(UUID player) {
    return executeRequestAsync(FriendsReply.class, "friends", singletonMap("uuid", player));
  }

  /**
   * @param player uuid of a player in string format, can be both dashed or undashed.
   * @return the future
   */
  public CompletableFuture<FriendsReply> getFriends(String player) {
    return executeRequestAsync(FriendsReply.class, "friends", singletonMap("uuid", player));
  }

  public CompletableFuture<GuildReply> getGuildByPlayer(UUID player) {
    return executeRequestAsync(GuildReply.class, "guild", singletonMap("player", player));
  }

  /**
   * @param player uuid of a player in string format, can be both dashed or undashed.
   * @return the future
   */
  public CompletableFuture<GuildReply> getGuildByPlayer(String player) {
    return executeRequestAsync(GuildReply.class, "guild", singletonMap("player", player));
  }

  public CompletableFuture<GuildReply> getGuildByName(String name) {
    return executeRequestAsync(GuildReply.class, "guild", singletonMap("name", name));
  }

  /**
   * @param id mongo id hex string
   * @return the future
   */
  public CompletableFuture<GuildReply> getGuildById(String id) {
    return executeRequestAsync(GuildReply.class, "guild", singletonMap("id", id));
  }

  /** You can directly get the guild using {@link HypixelAPI#getGuildByPlayer(UUID)} */
  @Deprecated
  public CompletableFuture<FindGuildReply> findGuildByPlayer(UUID player) {
    return executeRequestAsync(FindGuildReply.class, "findGuild", singletonMap("byUuid", player));
  }

  /** You can directly get the guild using {@link HypixelAPI#getGuildByPlayer(String)} */
  @Deprecated
  public CompletableFuture<FindGuildReply> findGuildByPlayer(String player) {
    return executeRequestAsync(FindGuildReply.class, "findGuild", singletonMap("byUuid", player));
  }

  /**
   * Finds a Guild by the name.
   *
   * @deprecated The Guild can be obtained directly by using {@link
   *     HypixelAPI#getGuildByName(String)}
   */
  @Deprecated
  public CompletableFuture<GuildReply> findGuildByName(String name) {
    return executeRequestAsync(GuildReply.class, "findGuild", singletonMap("byName", name));
  }

  public CompletableFuture<KeyReply> getKey() {
    return executeRequestAsync(KeyReply.class, "key");
  }

  public CompletableFuture<GameCountsReply> getGameCounts() {
    return executeRequestAsync(GameCountsReply.class, "gameCounts");
  }

  /**
   * Execute Request asynchronously, executes Callback when finished
   *
   * @param endpoint Request to get
   */
  private <ReplyT extends AbstractReply> CompletableFuture<ReplyT> executeRequestAsync(
      Class<ReplyT> replyType, String endpoint) {

    return executeRequestAsync(replyType, endpoint, Collections.emptyMap());
  }

  private <ReplyT extends AbstractReply> CompletableFuture<ReplyT> executeRequestAsync(
      Class<ReplyT> replyType, String endpoint, Map<String, Object> parameters) {

    CompletableFuture<ReplyT> future = new CompletableFuture<>();
    StringBuilder query = new StringBuilder();
    query.append(format("%s/%s?key=%s", SERVICE_URL_BASE, endpoint, apiKey.toString()));
    parameters.forEach((key, value) -> query.append(format("&%s=%s", key, value.toString())));
    executor.submit(() -> executeHttpRequest(query.toString(), replyType, future));
    return future;
  }

  private <ReplyT extends AbstractReply> void executeHttpRequest(
      String query, Class<ReplyT> replyType, CompletableFuture<ReplyT> completer) {

    ResponseHandler<ReplyT> decoder = response -> decodeUtf8JsonEntity(replyType, response);
    try {
      ReplyT reply = httpClient.execute(new HttpGet(query.toString()), decoder);
      if (reply != null) {
        reply.ensureValidity();
      }
      completer.complete(reply);
    } catch (Exception failure) {
      completer.completeExceptionally(failure);
    }
  }

  private <T> T decodeUtf8JsonEntity(Class<T> type, HttpResponse response) throws IOException {
    return GsonHolder.GSON.fromJson(EntityUtils.toString((response.getEntity()), "UTF-8"), type);
  }
}
