package net.hypixel.api.reply.skyblock;

import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import net.hypixel.api.reply.AbstractReply;

public class SkyBlockNewsReply extends AbstractReply {
    private JsonElement items;

    public JsonArray getItems() {
        if (items == null || items.isJsonNull()) {
            return null;
        } else {
            return items.getAsJsonArray();
        }
    }

    @Override
    public String toString() {
        return "SkyBlockNewsReply{" +
                "items=" + items +
                "} " + super.toString();
    }
}
