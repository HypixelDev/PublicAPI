package net.hypixel.api.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import java.lang.reflect.Type;
import net.hypixel.api.reply.PlayerReply.Player;

public class PlayerTypeAdapter implements JsonDeserializer<Player> {

    @Override
    public Player deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        return new Player(jsonElement);
    }
}
