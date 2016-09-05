package net.hypixel.api.adapters;

import com.google.gson.*;
import net.hypixel.api.reply.GuildReply;
import net.hypixel.api.util.APIUtil;

import java.lang.reflect.Type;

public class GuildCoinHistoryAdapter implements JsonDeserializer<GuildReply.Guild.GuildCoinHistory>, JsonSerializer<GuildReply.Guild.GuildCoinHistory> {

    @Override
    public JsonElement serialize(GuildReply.Guild.GuildCoinHistory src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }

    @Override
    public GuildReply.Guild.GuildCoinHistory deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        GuildReply.Guild.GuildCoinHistory history = new GuildReply.Guild.GuildCoinHistory();
        json.getAsJsonObject().entrySet().forEach(entry -> history.getCoinHistory().put(APIUtil.getDateTime(Long.parseLong(entry.getKey())), entry.getValue().getAsInt()));
        return history;
    }

}
