package net.hypixel.api.reply.skyblock;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hypixel.api.reply.RateLimitedReply;

public class SkyBlockGardenReply extends RateLimitedReply {
    private JsonElement garden;

    public JsonObject getGarden() {
        if (garden == null || garden.isJsonNull()) {
            return null;
        } else {
            return garden.getAsJsonObject();
        }
    }

    @Override
    public String toString() {
        return "SkyBlockGardenReply{" +
                "profile=" + garden +
                "} " + super.toString();
    }
}
