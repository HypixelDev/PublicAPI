package net.hypixel.api.reply;

import com.google.gson.annotations.SerializedName;
import net.hypixel.api.data.type.ServerType;

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

    public static class Session {

        /**
         * Boolean if player is online.
         * May be disabled in the player their settings, so may vary from player to player
         */
        private boolean online;

        /**
         * ServerType could be null if a new game/lobby has been released and type is not yet added.
         * <p>
         * This will NOT throw an exception.
         */
        @SerializedName("gameType")
        private ServerType serverType;

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

        public ServerType getServerType() {
            return serverType;
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
                    ", serverType=" + serverType +
                    ", mode=" + mode +
                    ", map=" + map +
                    "}";
        }
    }
}
