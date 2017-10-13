/* © 2017 Hypixel Inc. All Rights Reserved. */
package net.hypixel.api.adapters;

import com.google.gson.*;
import net.hypixel.api.util.GameType;

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
        String raw = json.getAsString();
        try {
            Integer intType = Integer.parseInt(raw);
            return GameType.fromId(intType);
        } catch (NumberFormatException ignored) {
        }
        return GameType.valueOf(raw);
    }

}
