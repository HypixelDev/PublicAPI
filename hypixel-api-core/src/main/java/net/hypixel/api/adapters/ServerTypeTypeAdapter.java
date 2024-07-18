package net.hypixel.api.adapters;

import com.google.gson.*;
import net.hypixel.data.type.GameType;
import net.hypixel.data.type.ServerType;

import java.lang.reflect.Type;

public class ServerTypeTypeAdapter implements JsonDeserializer<ServerType>, JsonSerializer<ServerType> {

    @Override
    public JsonElement serialize(ServerType src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.name());
    }

    @Override
    public ServerType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String raw = json.getAsString();
        try {
            return GameType.getById(Integer.parseInt(raw)).orElse(null);
        } catch (NumberFormatException ignored) {
        }
        return ServerType.valueOf(raw).orElse(null);
    }

}
