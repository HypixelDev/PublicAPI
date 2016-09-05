package net.hypixel.api.adapters;

import com.google.gson.*;
import net.hypixel.api.util.APIUtil;
import org.joda.time.DateTime;

import java.lang.reflect.Type;

/**
 * Our dates are always saved as a timestamp
 * if we diverge from that path we can adapt
 * it in here as well by just using some more
 * parsing.
 */
public class DateTimeTypeAdapter implements JsonDeserializer<DateTime>, JsonSerializer<DateTime> {

    @Override
    public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getMillis());
    }

    @Override
    public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return APIUtil.getDateTime(Long.parseLong(json.getAsString()));
    }

}
