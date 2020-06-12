package net.hypixel.api.reply;

import java.time.ZonedDateTime;
import java.util.List;
import net.hypixel.api.util.GameType;

public class RecentGamesReply extends AbstractReply {

  private List<GameSession> games;

  /**
   * @return Up to 100 of the player's most recently played games
   * @see GameSession
   */
  public List<GameSession> getGames() {
    return games;
  }

  @Override
  public String toString() {
    return "RecentGamesReply{" +
        "games=" + games +
        '}';
  }

  public static class GameSession {

    private ZonedDateTime date;
    private GameType      gameType;
    private String        mode;
    private String        map;
    private ZonedDateTime ended;

    /**
     * @return When the game started
     */
    public ZonedDateTime getStartDate() {
      return date;
    }

    /**
     * @return Game played during this session
     * @see net.hypixel.api.util.GameType
     */
    public GameType getGameType() {
      return gameType;
    }

    /**
     * @return Subtype of the game played (e.g. "solo_insane_lucky" for {@link GameType#SKYWARS})
     */
    public String getMode() {
      return mode;
    }

    /**
     * @return Map that was played on
     */
    public String getMap() {
      return map;
    }

    /**
     * @return When the game ended. If null, the game is ongoing
     */
    public ZonedDateTime getEndDate() {
      return ended;
    }

    @Override
    public String toString() {
      return "GameSession{" +
          "date=" + date +
          ", gameType=" + gameType +
          ", mode='" + mode + '\'' +
          ", map='" + map + '\'' +
          ", ended=" + ended +
          '}';
    }
  }
}
