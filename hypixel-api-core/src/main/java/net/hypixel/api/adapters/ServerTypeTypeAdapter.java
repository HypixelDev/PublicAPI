package net.hypixel.api.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.hypixel.api.data.type.GameType;
import net.hypixel.api.data.type.ServerType;

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
            return GameType.fromId(Integer.parseInt(raw));
        } catch (NumberFormatException ignored) {
        }
        return ServerType.valueOf(raw);
    }

}
