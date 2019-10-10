package net.hypixel.api.reply.skyblock;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hypixel.api.reply.AbstractReply;

public class SkyBlockProfileReply extends AbstractReply {
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
