package net.hypixel.api.reply;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SessionReply extends AbstractReply {
    private JsonElement session;

    /**
     * @return The session, or null if one wasn't found
     */
    public JsonObject getSession() {
        if(session.isJsonNull())
            return null;
        return session.getAsJsonObject();
    }

    @Override
    public String toString() {
        return "SessionReply{" +
                "session=" + session +
                ",super=" + super.toString() + "}";
    }
}
