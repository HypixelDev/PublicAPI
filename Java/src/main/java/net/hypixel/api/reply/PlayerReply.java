package net.hypixel.api.reply;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hypixel.api.request.RequestType;

@SuppressWarnings("unused")
public class PlayerReply extends AbstractReply {
    private JsonElement player;

    public JsonObject getPlayer() {
        if (player == null || player.isJsonNull()) {
            return null;
        } else {
            return player.getAsJsonObject();
        }
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.PLAYER;
    }

    @Override
    public String toString() {
        return "PlayerReply{" +
                "player=" + player +
                ",super=" + super.toString() + "}";
    }
}
