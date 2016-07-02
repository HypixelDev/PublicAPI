package net.hypixel.api.reply;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hypixel.api.request.RequestType;

@SuppressWarnings("unused")
public class SessionReply extends AbstractReply {
    private JsonElement session;

    /**
     * @return The session, or null if one wasn't found
     */
    public JsonObject getSession() {
        if (session == null || session.isJsonNull()) {
            return null;
        } else {
            return session.getAsJsonObject();
        }
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.SESSION;
    }

    @Override
    public String toString() {
        return "SessionReply{" +
                "session=" + session +
                ",super=" + super.toString() + "}";
    }
}
