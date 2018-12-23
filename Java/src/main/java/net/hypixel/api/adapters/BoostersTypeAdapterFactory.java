package net.hypixel.api.adapters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hypixel.api.reply.BoostersReply;

public class BoostersTypeAdapterFactory<T extends BoostersReply.Booster> extends CustomizedTypeAdapterFactory<T> {

    public BoostersTypeAdapterFactory(Class<T> customizedClass) {
        super(customizedClass);
    }

    @Override
    protected void afterRead(JsonElement json) {
        JsonObject obj = json.getAsJsonObject();

        JsonElement stackedElement = obj.get("stacked");
        if (stackedElement != null) {
            if (stackedElement.isJsonPrimitive()) {
                if (stackedElement.getAsJsonPrimitive().isBoolean()) {
                    obj.addProperty("queuedToStack", stackedElement.getAsJsonPrimitive().getAsBoolean());
                    obj.remove("stacked");
                }
            }
        }
    }
}