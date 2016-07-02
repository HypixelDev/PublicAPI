package net.hypixel.api.reply;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hypixel.api.request.RequestType;

@SuppressWarnings("unused")
public class GuildReply extends AbstractReply {
    private JsonElement guild;

    /**
     * @return The guild object, or null if one wasn't found
     */
    public JsonObject getGuild() {
        if (guild == null || guild.isJsonNull()) {
            return null;
        } else {
            return guild.getAsJsonObject();
        }
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.GUILD;
    }

    @Override
    public String toString() {
        return "GuildReply{" +
                "guild=" + guild +
                ",super=" + super.toString() + "}";
    }
}
