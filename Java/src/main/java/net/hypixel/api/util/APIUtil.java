package net.hypixel.api.util;

import java.util.UUID;

public class APIUtil {
    public static String stripDashes(UUID inputUuid) {
        String input = inputUuid.toString();
        return input.substring(0, 8) + input.substring(9, 13) + input.substring(14, 18) + input.substring(19, 23) + input.substring(24, 36);
    }
}
