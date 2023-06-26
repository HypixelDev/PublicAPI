package net.hypixel.api.reply.skyblock;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hypixel.api.reply.RateLimitedReply;

public class SkyBlockProfileReply extends RateLimitedReply {
    private JsonElement profile;

    public JsonObject getProfile() {
        if (profile == null || profile.isJsonNull()) {
            return null;
        } else {
            return profile.getAsJsonObject();
        }
    }

    @Override
    public String toString() {
        return "SkyBlockProfileReply{" +
                "profile=" + profile +
                "} " + super.toString();
    }
}
