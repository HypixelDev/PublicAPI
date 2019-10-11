package net.hypixel.api.reply.skyblock;

import com.google.gson.JsonObject;
import net.hypixel.api.reply.AbstractReply;

public class ResourceReply extends AbstractReply {
    private JsonObject response;

    public ResourceReply(JsonObject response) {
        this.response = response;
        this.success = response.has("success") && response.get("success").getAsBoolean();
        this.cause = response.has("cause") ? response.get("cause").getAsString() : null;
    }

    public JsonObject getResponse() {
        if (response == null || response.isJsonNull()) {
            return null;
        } else {
            return response.getAsJsonObject();
        }
    }

    @Override
    public String toString() {
        return "ResourceReply{" +
                "response=" + response +
                "} " + super.toString();
    }
}
