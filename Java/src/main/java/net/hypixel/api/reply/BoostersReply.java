package net.hypixel.api.reply;

import com.google.gson.JsonArray;
import net.hypixel.api.request.RequestType;

@SuppressWarnings("unused")
public class BoostersReply extends AbstractReply {
    private JsonArray boosters;

    public JsonArray getBoosters() {
        return boosters;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.BOOSTERS;
    }

    @Override
    public String toString() {
        return "BoostersReply{" +
                "boosters=" + boosters +
                ", super=" + super.toString() + "}";
    }
}
