package net.hypixel.api.reply;

import net.hypixel.api.util.GameType;

import java.util.Set;
import java.util.UUID;

/**
 * This method is deprecated and will be removed soon.
 */
@Deprecated
public class SessionReply extends AbstractReply {
    private Session session;

    /**
     * Session can be null if
     * 1) The player is in a lobby or offline
     * 2) The player has a staff rank
     *
     * @return The session, or null if either of above reasons is met
     */
    public Session getSession() {
        return session;
    }

    @Override
    public String toString() {
        return "SessionReply{" +
                "session=" + session +
                "} " + super.toString();
    }

    public class Session {
        /**
         * GameType could be null if a new game has been released
         * and GameType is not yet added to {@link GameType}.
         * <p>
         * This will NOT throw an exception.
         */
        private GameType gameType;
        /**
         * Server name for session
         */
        private String server;
        /**
         * Set of UUIDs of players currently in this session
         */
        private Set<UUID> players;

        public GameType getGameType() {
            return gameType;
        }

        @Deprecated
        public String getServer() {
            return server;
        }

        @Deprecated
        public Set<UUID> getPlayers() {
            return players;
        }

        @Override
        public String toString() {
            return "Session{" +
                    "gameType=" + gameType +
                    ", server='" + server + '\'' +
                    ", players=" + players +
                    '}';
        }
    }
}
