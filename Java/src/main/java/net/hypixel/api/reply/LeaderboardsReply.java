package net.hypixel.api.reply;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.hypixel.api.request.RequestType;
import net.hypixel.api.util.GameType;

@SuppressWarnings("unused")
public class LeaderboardsReply extends AbstractReply {
    private Map<GameType, List<GameLeaderboard>> leaderboards;

    public Map<GameType, List<GameLeaderboard>> getLeaderboards() {
        return leaderboards;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.LEADERBOARDS;
    }

    @Override
    public String toString() {
        return "LeaderboardsReply{" +
                "leaderboards=" + leaderboards +
                ",super=" + super.toString() + "}";
    }

    public class GameLeaderboard {
        private String path;
        private String prefix;
        private int count;
        private String location;
        private List<UUID> leaders;
        private String title;

        public String getPath() {
            return path;
        }

        public String getPrefix() {
            return prefix;
        }

        public int getCount() {
            return count;
        }

        public String getLocation() {
            return location;
        }

        public List<UUID> getLeaders() {
            return leaders;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "GameLeaderboard{" +
                    "path=" + path +
                    ", prefix=" + prefix +
                    ", count=" + count +
                    ", location=" + location +
                    ", leaders=" + leaders +
                    ", title=" + title +
                    '}';
        }
    }
}
