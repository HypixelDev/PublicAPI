package net.hypixel.api.reply.skyblock;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hypixel.api.reply.RateLimitedReply;

public class SkyBlockMuseumReply extends RateLimitedReply {
    private JsonElement museum;

    public JsonObject getMuseum() {
        if (museum == null || museum.isJsonNull()) {
            return null;
        } else {
            return museum.getAsJsonObject();
        }
    }

    @Override
    public String toString() {
        return "SkyBlockMuseumReply{" +
                "profile=" + museum +
                "} " + super.toString();
    }
}
