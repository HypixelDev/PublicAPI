package net.hypixel.api.reply.skyblock;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.hypixel.api.reply.AbstractReply;

public class SkyBlockProfilesReply extends AbstractReply {
    private JsonElement profiles;

    public JsonArray getProfiles() {
        if (profiles == null || profiles.isJsonNull()) {
            return null;
        } else {
            return profiles.getAsJsonArray();
        }
    }

    @Override
    public String toString() {
        return "SkyBlockProfilesReply{" +
                "profiles=" + profiles +
                "} " + super.toString();
    }
}
