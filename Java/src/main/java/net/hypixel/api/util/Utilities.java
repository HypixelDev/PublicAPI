package net.hypixel.api.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

public class Utilities {

    public static ZonedDateTime getDateTime(long timeStamp) {
        return Instant.ofEpochMilli(timeStamp).atZone(ZoneId.of("America/New_York"));
    }

    public static UUID uuidFromString(String uuidStr) {
        if (uuidStr.contains("-")) {
            return UUID.fromString(uuidStr);
        } else {
            return UUID.fromString(uuidStr.substring(0, 8) + "-" + uuidStr.substring(8, 12) + "-" + uuidStr.substring(12, 16) + "-" + uuidStr.substring(16, 20) + "-" + uuidStr.substring(20, 32));
        }
    }
}
