package net.hypixel.api.reply;

import com.google.gson.annotations.SerializedName;

import net.hypixel.api.request.RequestType;

@SuppressWarnings("unused")
public class WatchdogStatsReply extends AbstractReply {

    private WatchdogStats stats;

    public WatchdogStats getStats() {
        return stats;
    }

    @Override
    public String toString() {
        return "WatchdogStatsReply{" +
                "stats=" + stats +
                ", super=" + super.toString() + "}";
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.WATCHDOG_STATS;
    }

    public class WatchdogStats {

        @SerializedName("watchdog_lastMinute")
        private int watchdogBansLastMinute;
        @SerializedName("staff_rollingDaily")
        private int staffDailyBans;
        @SerializedName("watchdog_total")
        private int totalWatchdogBans;
        @SerializedName("watchdog_rollingDaily")
        private int watchdogDailyBans;
        @SerializedName("staff_total")
        private int totalStaffBans;

        public int getWatchdogBansLastMinute() {
            return watchdogBansLastMinute;
        }

        public int getStaffDailyBans() {
            return staffDailyBans;
        }

        public int getTotalWatchdogBans() {
            return totalWatchdogBans;
        }

        public int getWatchdogDailyBans() {
            return watchdogDailyBans;
        }

        public int getTotalStaffBans() {
            return totalStaffBans;
        }

        @Override
        public String toString() {
            return "WatchdogStats{" +
                    "watchdogBansLastMinute=" + watchdogBansLastMinute +
                    ", staffDailyBans=" + staffDailyBans +
                    ", totalWatchdogBans=" + totalWatchdogBans +
                    ", watchdogDailyBans=" + watchdogDailyBans +
                    ", totalStaffBans=" + totalStaffBans +
                    '}';
        }
    }
}
