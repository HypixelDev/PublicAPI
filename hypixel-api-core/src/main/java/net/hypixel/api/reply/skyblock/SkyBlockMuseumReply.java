package net.hypixel.api.reply.skyblock;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hypixel.api.reply.RateLimitedReply;

public class SkyBlockMuseumReply extends RateLimitedReply {
    private JsonElement members;

    public JsonObject getMuseum() {
        if (members == null || members.isJsonNull()) {
            return null;
        } else {
            return members.getAsJsonObject();
        }
    }

    @Override
    public String toString() {
        return "SkyBlockMuseumReply{" +
                "profile=" + members +
                "} " + super.toString();
    }
}
