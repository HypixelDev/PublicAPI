package net.hypixel.api.reply;

import com.google.gson.JsonArray;

@SuppressWarnings("unused")
public class BoostersReply extends AbstractReply {
    private JsonArray boosters;

    public JsonArray getBoosters() {
        return boosters;
    }

    @Override
    public String toString() {
        return "BoostersReply{" +
                "boosters=" + boosters +
                ",super=" + super.toString() + "}";
    }
}
