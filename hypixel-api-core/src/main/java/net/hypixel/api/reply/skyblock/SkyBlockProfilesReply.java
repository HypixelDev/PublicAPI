package net.hypixel.api.reply.skyblock;

import com.google.gson.JsonObject;
import net.hypixel.api.reply.AbstractReply;

import java.util.List;

public class SkyBlockProfilesReply extends AbstractReply {
    private List<JsonObject> profiles;

    public List<JsonObject> getProfiles() {
        return profiles;
    }

    @Override
    public String toString() {
        return "SkyBlockProfilesReply{" +
                "profiles=" + profiles +
                "} " + super.toString();
    }
}
