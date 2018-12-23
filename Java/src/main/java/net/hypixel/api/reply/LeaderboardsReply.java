package net.hypixel.api.reply;

import net.hypixel.api.util.GameType;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LeaderboardsReply extends AbstractReply {
    private Map<GameType, List<Leaderboard>> leaderboards;

    public Map<GameType, List<Leaderboard>> getLeaderboards() {
        return leaderboards;
    }

    @Override
    public String toString() {
        return "LeaderboardsReply{" +
                "leaderboards=" + leaderboards +
                "} " + super.toString();
    }

    public class Leaderboard {

        private String path;
        private String prefix;
        private int count;
        private List<UUID> leaders;
        private String title;

        public Leaderboard() {
        }

        public String getPath() {
            return path;
        }

        public String getPrefix() {
            return prefix;
        }

        public int getCount() {
            return count;
        }

        public List<UUID> getLeaders() {
            return leaders;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "Leaderboard{" +
                    "path='" + path + '\'' +
                    ", prefix='" + prefix + '\'' +
                    ", count=" + count +
                    ", leaders=" + leaders +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

}
