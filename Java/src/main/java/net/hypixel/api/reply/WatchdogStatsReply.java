package net.hypixel.api.reply;

import com.google.gson.annotations.SerializedName;

public class WatchdogStatsReply extends AbstractReply {
    @SerializedName("staff_rollingDaily")
    private int staffRollingDaily;
    @SerializedName("staff_total")
    private int staffTotal;
    @SerializedName("watchdog_total")
    private int watchdogTotal;
    @SerializedName("watchdog_lastMinute")
    private int watchdogLastMinute;
    @SerializedName("watchdog_rollingDaily")
    private int watchdogRollingDaily;

    public int getStaffRollingDaily() {
        return staffRollingDaily;
    }

    public int getStaffTotal() {
        return staffTotal;
    }

    public int getWatchdogTotal() {
        return watchdogTotal;
    }

    public int getWatchdogLastMinute() {
        return watchdogLastMinute;
    }

    public int getWatchdogRollingDaily() {
        return watchdogRollingDaily;
    }

    @Override
    public String toString() {
        return "WatchdogStatsReply{" +
                "staffRollingDaily=" + staffRollingDaily +
                ", staffTotal=" + staffTotal +
                ", watchdogTotal=" + watchdogTotal +
                ", watchdogLastMinute=" + watchdogLastMinute +
                ", watchdogRollingDaily=" + watchdogRollingDaily +
                "} " + super.toString();
    }
}
