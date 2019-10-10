package net.hypixel.api.reply.skyblock;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hypixel.api.reply.AbstractReply;

public class SkyBlockCollectionsReply extends AbstractReply {
    private JsonElement collections;

    public JsonObject getCollections() {
        if (collections == null || collections.isJsonNull()) {
            return null;
        } else {
            return collections.getAsJsonObject();
        }
    }

    @Override
    public String toString() {
        return "SkyBlockCollectionsReply{" +
                "collections=" + collections +
                "} " + super.toString();
    }
}
