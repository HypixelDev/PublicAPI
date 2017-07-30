package net.hypixel.api.adapters;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hypixel.api.reply.GuildReply;
import net.hypixel.api.util.APIUtil;

import java.util.Calendar;
import java.util.List;

public class GuildCoinHistoryHoldingTypeAdapterFactory<T extends GuildReply.GuildCoinHistoryHolding> extends CustomizedTypeAdapterFactory<T> {

    public GuildCoinHistoryHoldingTypeAdapterFactory(Class<T> customizedClass) {
        super(customizedClass);
    }

    @Override
    protected void afterRead(JsonElement json) {
        JsonObject obj = json.getAsJsonObject();

        // parse the coin history
        GuildReply.Guild.GuildCoinHistory history = new GuildReply.Guild.GuildCoinHistory();
        List<String> toRemove = Lists.newArrayList();
        json.getAsJsonObject().entrySet().forEach(entry -> {
            if (entry.getKey().startsWith("dailyCoins")) {
                String[] split = entry.getKey().split("-");
                int day = Integer.parseInt(split[1]);
                int month = Integer.parseInt(split[2]);
                int year = Integer.parseInt(split[3]);

                Calendar c = Calendar.getInstance();
                c.set(year, month, day, 0, 0);
                history.getCoinHistory().put(APIUtil.getDateTime(c.getTime().getTime()), entry.getValue().getAsInt());
                toRemove.add(entry.getKey());
            }
        });

        // remove dailyCoins-%d-%d-%d from the original object
        toRemove.forEach(obj::remove);

        JsonObject coinHistory = new JsonObject();
        // insert as millisecond string so we can use our standard milli -> datetime conversion
        history.getCoinHistory().entrySet().forEach(entry -> coinHistory.addProperty(String.valueOf(entry.getKey().toInstant().toEpochMilli()), entry.getValue()));

        // load into the json
        obj.add("guildCoinHistory", coinHistory);
    }
}
