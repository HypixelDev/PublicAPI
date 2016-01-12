package net.hypixel.api.util;

import java.util.UUID;
import java.util.regex.Pattern;

public class APIUtil {

    private static Pattern dashPattern = Pattern.compile("-");
    private static Pattern uuidPattern = Pattern.compile("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})");
    
    public static String stripDashes(UUID inputUuid) {
        return dashPattern.matcher(inputUuid.toString()).replaceAll("");
    }

    public static UUID withoutHyphens(String stripped) {
        return UUID.fromString(uuidPattern.matcher(stripped).replaceAll("$1-$2-$3-$4-$5"));
    }
}
