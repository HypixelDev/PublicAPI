package net.hypixel.api.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;
import net.hypixel.api.adapters.BoostersTypeAdapterFactory;
import net.hypixel.api.adapters.DateTimeTypeAdapter;
import net.hypixel.api.adapters.GameTypeTypeAdapter;
import net.hypixel.api.adapters.PlayerTypeAdapter;
import net.hypixel.api.adapters.UUIDTypeAdapter;
import net.hypixel.api.reply.BoostersReply;
import net.hypixel.api.reply.PlayerReply.Player;

public class Utilities {

    public static final Gson GSON = new GsonBuilder()
        .registerTypeAdapter(UUID.class, new UUIDTypeAdapter())
        .registerTypeAdapter(GameType.class, new GameTypeTypeAdapter())
        .registerTypeAdapter(ZonedDateTime.class, new DateTimeTypeAdapter())
        .registerTypeAdapter(Player.class, new PlayerTypeAdapter())
        .registerTypeAdapterFactory(new BoostersTypeAdapterFactory<>(BoostersReply.Booster.class))
        .create();

    public static ZonedDateTime getDateTime(long timeStamp) {
        return Instant.ofEpochMilli(timeStamp).atZone(ZoneId.of("America/New_York"));
    }

    public static UUID uuidFromString(String uuidStr) {
        if (!uuidStr.contains("-")) {
            uuidStr = uuidStr.substring(0, 8) + "-"
                + uuidStr.substring(8, 12) + "-"
                + uuidStr.substring(12, 16) + "-"
                + uuidStr.substring(16, 20) + "-"
                + uuidStr.substring(20, 32);

        }
        return UUID.fromString(uuidStr);
    }
}
