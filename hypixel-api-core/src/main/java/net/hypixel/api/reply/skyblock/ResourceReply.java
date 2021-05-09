package net.hypixel.api.reply.skyblock;

import com.google.gson.JsonObject;
import net.hypixel.api.reply.AbstractReply;

public class ResourceReply extends AbstractReply {

    private long lastUpdated;
    private JsonObject response;

    public ResourceReply(JsonObject response) {
        this.response = response;
        this.success = response.has("success") && response.get("success").getAsBoolean();
        this.cause = response.has("cause") ? response.get("cause").getAsString() : null;
        this.lastUpdated = response.has("lastUpdated") ? response.get("lastUpdated").getAsLong() : -1;
    }

    public JsonObject getResponse() {
        if (response == null || response.isJsonNull()) {
            return null;
        } else {
            return response.getAsJsonObject();
        }
    }

    /**
     * Gets unix time when the resource was updated.
     * Will return -1 if last updated was not included in response
     * @return long unix time
     */
    public long getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public String toString() {
        return "ResourceReply{" +
                "lastUpdated=" + lastUpdated +
                ", response=" + response +
                "} " + super.toString();
    }
}
