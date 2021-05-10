package net.hypixel.api.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Utilities {

    public static ZonedDateTime getDateTime(long timeStamp) {
        return Instant.ofEpochMilli(timeStamp).atZone(ZoneId.of("America/New_York"));
    }
}
