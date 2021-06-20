package net.hypixel.api.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

/**
 * An object returned from the Hypixel API that lacks a defined structure.
 */
public abstract class UnstableHypixelObject {

    protected final JsonObject raw;

    protected UnstableHypixelObject(JsonElement raw) {
        this.raw = raw instanceof JsonObject
            ? (JsonObject) raw
            : new JsonObject();
    }

    /**
     * @return The raw object returned by the Hypixel API; the source of any properties for the
     * object
     */
    public JsonObject getRaw() {
        return raw;
    }

    /**
     * @param key Dot-notation path to the desired field
     * @return {@code true} if the object has a value associated with the {@code key}, including if
     * that value is {@link JsonNull}. Otherwise {@code false}.
     * @see #getProperty(String)
     */
    public boolean hasProperty(String key) {
        return getProperty(key) != null;
    }

    /**
     * Get a String from the object
     *
     * @return The string value associated with the {@code key}, or {@code defaultValue} if the
     * value does not exist or isn't a string
     * @see #getProperty(String)
     */
    public String getStringProperty(String key, String defaultValue) {
        JsonElement value = getProperty(key);
        if (value == null
            || !value.isJsonPrimitive()
            || !value.getAsJsonPrimitive().isString()) {
            return defaultValue;
        }
        return value.getAsJsonPrimitive().getAsString();
    }

    /**
     * Get a float from the object
     *
     * @return The float value associated with the {@code key}, or {@code defaultValue} if the value
     * does not exist or isn't a float
     * @see #getProperty(String)
     */
    public float getFloatProperty(String key, float defaultValue) {
        return getNumberProperty(key, defaultValue).floatValue();
    }

    /**
     * Get a double from the object
     *
     * @return The double value associated with the {@code key}, or {@code defaultValue} if the
     * value does not exist or isn't a double
     * @see #getProperty(String)
     */
    public double getDoubleProperty(String key, double defaultValue) {
        return getNumberProperty(key, defaultValue).doubleValue();
    }

    /**
     * Get a long from the object
     *
     * @return The long value associated with the {@code key}, or {@code defaultValue} if the value
     * does not exist or isn't a long
     * @see #getProperty(String)
     */
    public long getLongProperty(String key, long defaultValue) {
        return getNumberProperty(key, defaultValue).longValue();
    }

    /**
     * Get an integer from the object
     *
     * @return The int value associated with the {@code key}, or {@code defaultValue} if the value
     * does not exist or isn't an int
     * @see #getProperty(String)
     */
    public int getIntProperty(String key, int defaultValue) {
        return getNumberProperty(key, defaultValue).intValue();
    }

    /**
     * Get a Number property from the object
     *
     * @return The numeric value associated with the {@code key}, or {@code defaultValue} if the
     * value does not exist or isn't a number
     * @see #getProperty(String)
     */
    public Number getNumberProperty(String key, Number defaultValue) {
        JsonElement value = getProperty(key);
        if (value == null
            || !value.isJsonPrimitive()
            || !value.getAsJsonPrimitive().isNumber()) {
            return defaultValue;
        }
        return value.getAsJsonPrimitive().getAsNumber();
    }

    /**
     * Get a boolean from the object
     *
     * @return The boolean value associated with the {@code key}, or {@code defaultValue} if the
     * value does not exist or isn't a boolean
     * @see #getProperty(String)
     */
    public boolean getBooleanProperty(String key, boolean defaultValue) {
        JsonElement value = getProperty(key);
        if (value == null
            || !value.isJsonPrimitive()
            || !value.getAsJsonPrimitive().isBoolean()) {
            return defaultValue;
        }
        return value.getAsJsonPrimitive().getAsBoolean();
    }

    /**
     * Get a JsonArray property from the object
     *
     * @return The JSON array associated with the {@code key}, or an empty array if the value does
     * not exist or isn't an array
     * @see #getProperty(String)
     */
    public JsonArray getArrayProperty(String key) {
        JsonElement result = getProperty(key);
        if (result == null || !result.isJsonArray()) {
            return new JsonArray();
        }
        return result.getAsJsonArray();
    }

    /**
     * Get a JsonObject property from the object
     *
     * @return The JSON object associated with the {@code key}, or {@code null} if the value does
     * not exist or isn't a JSON object
     * @see #getProperty(String)
     */
    public JsonObject getObjectProperty(String key) {
        JsonElement result = getProperty(key);
        if (result == null || !result.isJsonObject()) {
            return null;
        }
        return result.getAsJsonObject();
    }

    /**
     * Read a property from the object returned by the API
     *
     * @param key Dot-notation path to the desired field (e.g. {@code "stats.SkyWars.deaths"})
     * @return The value associated with the specified property, or {@code null} if no value is set
     * for that property.
     */
    public JsonElement getProperty(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Property key cannot be null");
        } else if (key.isEmpty()) {
            // Return root object if path is empty.
            return raw;
        }

        String[] tokens = Utilities.tokenizeKey(key);

        // Navigate the raw object until the end of the provided token list.
        JsonObject parent = getRaw();
        for (int i = 0; i < tokens.length; i++) {

            JsonElement child = parent.get(tokens[i].replace("\\.", "."));
            if (i + 1 == tokens.length) {
                // No more tokens; current child must be the output.
                return child;
            }

            // More tokens follow; child must be an object to continue.
            if (child instanceof JsonObject) {
                parent = child.getAsJsonObject();
                continue;
            }
            break;
        }

        return null;
    }
}
