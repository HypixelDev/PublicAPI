package net.hypixel.api.util;

import java.util.UUID;
import java.util.function.Function;
import java.util.regex.Pattern;

public class APIUtil {

    private static Pattern dashPattern = Pattern.compile("-");
    public final static Function<Object, String> UUID_STRIPPER = value -> APIUtil.stripDashes((UUID) value);
    private static Pattern uuidPattern = Pattern.compile("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})");

    public static String stripDashes(UUID inputUuid) {
        return dashPattern.matcher(inputUuid.toString()).replaceAll("");
    }

    public static UUID withDashes(String stripped) {
        return UUID.fromString(uuidPattern.matcher(stripped).replaceAll("$1-$2-$3-$4-$5"));
    }
}
