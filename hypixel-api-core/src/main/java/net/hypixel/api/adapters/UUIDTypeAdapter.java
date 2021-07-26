package net.hypixel.api.adapters;

import com.google.gson.*;
import net.hypixel.api.util.Utilities;

import java.lang.reflect.Type;
import java.util.UUID;

public class UUIDTypeAdapter implements JsonDeserializer<UUID>, JsonSerializer<UUID> {

    @Override
    public JsonElement serialize(UUID src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }

    @Override
    public UUID deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Utilities.uuidFromString(json.getAsString());
    }
}
