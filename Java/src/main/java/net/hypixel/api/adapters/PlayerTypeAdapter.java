package net.hypixel.api.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import net.hypixel.api.reply.PlayerReply.Player;

public class PlayerTypeAdapter implements JsonDeserializer<Player> {

    @Override
    public Player deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        try {
            Player player = new Player();

            // Set raw player data using reflection
            Field rawPlayerField = Player.class.getDeclaredField("raw");
            rawPlayerField.setAccessible(true);
            rawPlayerField.set(player, jsonElement);

            return player;

        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }
}
