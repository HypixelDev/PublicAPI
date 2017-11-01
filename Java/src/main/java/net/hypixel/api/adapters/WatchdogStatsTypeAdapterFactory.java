package net.hypixel.api.adapters;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import net.hypixel.api.reply.WatchdogStatsReply;

/**
 * We need this adapter because watchdogstats reply
 * does not return the fields in a nested json object.
 * Also the field names aren't really nice.
 */
public class WatchdogStatsTypeAdapterFactory extends CustomizedTypeAdapterFactory<WatchdogStatsReply> {
    private final List<String> fields;

    public WatchdogStatsTypeAdapterFactory(Class<WatchdogStatsReply> customizedClass) {
        super(customizedClass);
        this.fields = new ArrayList<>();
        readFields();
    }

    private void readFields() {
        Field[] cfields = WatchdogStatsReply.WatchdogStats.class.getDeclaredFields();
        for (Field field : cfields) {
            String name;
            SerializedName annotation = field.getAnnotation(SerializedName.class);
            if (annotation != null) {
                name = annotation.value();
            } else {
                name = field.getName();
                // ignore the outer class instance reference in inner class.
                if (name.startsWith("this$")) {
                    continue;
                }
            }
            fields.add(name);
        }
    }

    @Override
    protected void beforeWrite(WatchdogStatsReply source, JsonElement toSerialize) {
        JsonObject jobject = toSerialize.getAsJsonObject();
        JsonElement jselement = jobject.remove("stats");
        // transform the json back as how it was
        if (jselement != null && jselement instanceof JsonObject) {
            JsonObject jsobject = jselement.getAsJsonObject();
            jsobject.entrySet().forEach(e -> jobject.add(e.getKey(), e.getValue()));
        }
    }

    @Override
    protected void afterRead(JsonElement deserialized) {
        JsonObject jobject = deserialized.getAsJsonObject();
        if (!jobject.get("success").getAsBoolean()) {
            return;
        }
        JsonObject statsobj = new JsonObject();
        // parse the watchdog stats
        fields.forEach(k -> {
            JsonElement jfield = jobject.get(k);
            if (jfield != null) {
                statsobj.addProperty(k, jfield.getAsInt());
            }
        });
        jobject.add("stats", statsobj);
    }
}