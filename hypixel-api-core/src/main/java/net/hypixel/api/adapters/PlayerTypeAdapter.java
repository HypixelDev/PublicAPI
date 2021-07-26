package net.hypixel.api.adapters;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import net.hypixel.api.reply.PlayerReply.Player;

import java.io.IOException;

public class PlayerTypeAdapter extends TypeAdapter<Player> {

    private final TypeAdapter<JsonElement> defaultAdapter;

    public PlayerTypeAdapter() {
        defaultAdapter = new Gson().getAdapter(JsonElement.class);
    }

    @Override
    public void write(JsonWriter out, Player value) throws IOException {
        defaultAdapter.write(out, value.getRaw());
    }

    @Override
    public Player read(JsonReader in) throws IOException {
        JsonToken type = in.peek();
        if (type == JsonToken.NULL) {
            in.nextNull();
            return new Player(null);
        }
        return new Player(defaultAdapter.read(in));
    }
}
