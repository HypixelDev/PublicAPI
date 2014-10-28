package net.hypixel.api.reply;

import com.google.gson.JsonArray;

@SuppressWarnings("unused")
public class FriendsReply extends AbstractReply {
    private JsonArray records;

    public JsonArray getRecords() {
        return records;
    }

    @Override
    public String toString() {
        return "FriendsReply{" +
                "records=" + records +
                ",super=" + super.toString() + "}";
    }
}
