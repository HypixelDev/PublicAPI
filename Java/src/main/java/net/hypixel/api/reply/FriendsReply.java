package net.hypixel.api.reply;

import com.google.gson.JsonArray;
import net.hypixel.api.request.RequestType;

@SuppressWarnings("unused")
public class FriendsReply extends AbstractReply {
    private JsonArray records;

    public JsonArray getRecords() {
        return records;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FRIENDS;
    }

    @Override
    public String toString() {
        return "FriendsReply{" +
                "records=" + records +
                ",super=" + super.toString() + "}";
    }
}
