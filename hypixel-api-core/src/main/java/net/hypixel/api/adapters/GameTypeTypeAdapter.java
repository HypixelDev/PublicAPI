package net.hypixel.api.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.hypixel.api.data.type.GameType;

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
            return GameType.fromId(json.getAsInt());
        }

        String raw = json.getAsString();
        try {
            return GameType.fromId(Integer.parseInt(raw));
        } catch (NumberFormatException ignored) {
        }

        try {
            return GameType.valueOf(raw);
        } catch (IllegalArgumentException ignored) {
        }

        return null;
    }

}
