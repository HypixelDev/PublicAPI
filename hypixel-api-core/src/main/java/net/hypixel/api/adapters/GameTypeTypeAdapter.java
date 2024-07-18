package net.hypixel.api.adapters;

import com.google.gson.*;
import net.hypixel.data.type.GameType;

import java.lang.reflect.Type;

/**
 * We need this adapter because we note GameTypes
 * as both the id and as it's enum name
 */
public class GameTypeTypeAdapter implements JsonDeserializer<GameType>, JsonSerializer<GameType> {

    @Override
    public JsonElement serialize(GameType src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }

    @Override
    public GameType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isNumber()) {
            return GameType.getById(json.getAsInt()).orElse(null);
        }

        String raw = json.getAsString();
        try {
            return GameType.getById(Integer.parseInt(raw)).orElse(null);
        } catch (NumberFormatException ignored) {
        }

        try {
            return GameType.valueOf(raw);
        } catch (IllegalArgumentException ignored) {
        }

        try {
            return GameType.getByDatabaseName(raw).orElse(null);
        } catch (IllegalArgumentException ignored) {
        }

        return null;
    }

}
