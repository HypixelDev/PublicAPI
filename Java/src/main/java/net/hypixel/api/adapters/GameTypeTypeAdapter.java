package net.hypixel.api.adapters;

import com.google.gson.*;
import net.hypixel.api.util.GameType;

import java.lang.reflect.Type;

public class GameTypeTypeAdapter implements JsonDeserializer<GameType>, JsonSerializer<GameType> {

    @Override
    public JsonElement serialize(GameType src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }

    @Override
    public GameType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String raw = json.getAsString();
        try {
            Integer intType = Integer.parseInt(raw);
            return GameType.fromId(intType);
        } catch (NumberFormatException ignored) {
        }
        return GameType.valueOf(raw);
    }

}
