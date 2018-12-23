package net.hypixel.api.reply;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@SuppressWarnings("unused")
public class LeaderboardsReply extends AbstractReply {
    private JsonElement leaderboards;

    public JsonObject getLeaderboards() {
        if (leaderboards == null || leaderboards.isJsonNull()) {
            return null;
        } else {
            return leaderboards.getAsJsonObject();
        }
    }

    @Override
    public String toString() {
        return "LeaderboardsReply{" +
                "leaderboards=" + leaderboards +
                ", super=" + super.toString() + "}";
    }
}
