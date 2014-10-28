package net.hypixel.api.reply;

import com.google.gson.JsonObject;

@SuppressWarnings("unused")
public class GuildReply extends AbstractReply {
    private JsonObject guild;

    /**
     * @return The guild object, or null if one wasn't found
     */
    public JsonObject getGuild() {
        return guild;
    }

    @Override
    public String toString() {
        return "GuildReply{" +
                "guild=" + guild +
                ",super=" + super.toString() + "}";
    }
}
