package net.hypixel.api.reply;

import com.google.gson.JsonObject;

@SuppressWarnings("unused")
public class PlayerReply extends AbstractReply {
    private JsonObject player;

    public JsonObject getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "PlayerReply{" +
                "player=" + player +
                ",super=" + super.toString() + "}";
    }
}
