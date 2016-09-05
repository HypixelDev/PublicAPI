package net.hypixel.api.adapters;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Our dates are always saved as a timestamp
 * if we diverge from that path we can adapt
 * it in here as well by just using some more
 * parsing.
 */
public class DateTypeAdapter implements JsonDeserializer<Date>, JsonSerializer<Date> {

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String date = json.getAsString();
        return new Date(Long.parseLong(date));
    }

}
