package net.hypixel.api.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Nullicorn
 */
public class PropertyFilter {

    public static PropertyFilter with(String... includedKeys) {
        PropertyFilter filter = new PropertyFilter();
        filter.include(includedKeys);
        return filter;
    }

    private final Set<String> allowedKeys;

    public PropertyFilter() {
        allowedKeys = new HashSet<>();
    }

    public void include(String... keys) {
        allowedKeys.addAll(keysToList(keys));
    }

    public void removeKeys(String... keys) {
        allowedKeys.removeAll(keysToList(keys));
    }

    private List<String> keysToList(String... keys) {
        List<String> keyList = Arrays.asList(keys);
        if (keyList.contains(null)) {
            throw new IllegalArgumentException("Filtered keys cannot be null");
        }
        return keyList;
    }

    public void applyTo(ComplexHypixelObject object) {
        JsonObject json = object.getRaw();
        if (json == null || json.entrySet().isEmpty()) {
            return;
        }

        JsonObject temp = new JsonObject();
        for (String key : allowedKeys) {
            JsonElement value = object.getProperty(key);
            if (value == null) {
                // Ignore null properties.
                continue;
            }

            // Tokenize the path at un-escaped periods.
            // The negative-lookbehind ensures that periods are not preceded by a backslash.
            String[] tokens = key.split("(?<!\\\\)\\.");

            JsonObject parent = temp;
            for (int i = 0; i < tokens.length; i++) {
                if (i < tokens.length - 1) {
                    // Use the existing child object (if one exists).
                    JsonElement existingChild = parent.get(tokens[i]);
                    if (existingChild instanceof JsonObject) {
                        parent = (JsonObject) existingChild;
                        continue;
                    }

                    // Create a new child object if one doesn't exist.
                    JsonObject child = new JsonObject();
                    parent.add(tokens[i], child);
                    parent = child;
                } else {
                    // Set the final value of the property.
                    parent.add(tokens[i], value);
                }
            }
        }

        // Replace the contents of the original object.
        json.entrySet().clear();
        for (Entry<String, JsonElement> property : temp.entrySet()) {
            json.add(property.getKey(), property.getValue());
        }
    }
}
