package net.hypixel.api.reply;

import net.hypixel.api.util.GameType;

import java.util.Optional;

public class StatusReply extends AbstractReply {

    /**
     * {@link StatusReply.Session} instance of player
     */
    private Session session;

    public Session getSession() {
        return session;
    }

    @Override
    public String toString() {

        return "StatusReply{" +
                "session=" + session +
                "} " + super.toString();
    }

    public class Session {

        /**
         * Boolean if player is online.
         * May be disabled in the player their settings, so may vary from player to player
         */
        private boolean online;

        /**
         * GameType could be null if a new game has been released
         * and GameType is not yet added to {@link GameType}.
         * <p>
         * This will NOT throw an exception.
         */
        private GameType gameType;

        /**
         * Mode of game being played
         * Will be "lobby" if player is in lobby
         */
        private String mode;

        /**
         * Map being played
         * Will be null if player is not in game
         **/
        private String map;

        public boolean isOnline() {
            return online;
        }

        public GameType getGameType() {
            return gameType;
        }

        public String getMode() {
            return mode;
        }

        public String getMap() {
            return map;
        }

        @Override
        public String toString() {
            return "Session{" +
                    "online=" + online +
                    ", gameType=" + gameType +
                    ", mode=" + mode +
                    ", map=" + map +
                    "}";
        }
    }
}
