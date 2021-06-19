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
     * @return Whether or not the object has a property set at the given path, including if its
     * value is explicitly {@link JsonNull null}.
     */
    public boolean hasProperty(String key) {
        return getProperty(key) != null;
    }

    /**
     * Get a String from the object
     *
     * @param def The default value to return if the property was not found
     * @return String with the specified key, or def if the property was not found
     * @see #getProperty(String)
     */
    public String getStringProperty(String key, String def) {
        JsonElement value = getProperty(key);
        if (value == null
            || !value.isJsonPrimitive()
            || !value.getAsJsonPrimitive().isString()) {
            return def;
        }
        return value.getAsJsonPrimitive().getAsString();
    }

    /**
     * Get a float from the object
     *
     * @param def The default value to return if the property was not found
     * @return float with the specified key, or def if the property was not found
     * @see #getProperty(String)
     */
    public float getFloatProperty(String key, float def) {
        return getNumberProperty(key, def).floatValue();
    }

    /**
     * Get a double from the object
     *
     * @param def The default value to return if the property was not found
     * @return double with the specified key, or def if the property was not found
     * @see #getProperty(String)
     */
    public double getDoubleProperty(String key, double def) {
        return getNumberProperty(key, def).doubleValue();
    }

    /**
     * Get a long from the object
     *
     * @param def The default value to return if the property was not found
     * @return long with the specified key, or def if the property was not found
     * @see #getProperty(String)
     */
    public long getLongProperty(String key, long def) {
        return getNumberProperty(key, def).longValue();
    }

    /**
     * Get an integer from the object
     *
     * @param def The default value to return if the property was not found
     * @return int with the specified key, or def if the property was not found
     * @see #getProperty(String)
     */
    public int getIntProperty(String key, int def) {
        return getNumberProperty(key, def).intValue();
    }

    /**
     * Get a Number property from the object
     *
     * @param def The default value to return if the property was not found
     * @return Number with the specified key, or def if the property was not found
     * @see #getProperty(String)
     */
    public Number getNumberProperty(String key, Number def) {
        JsonElement value = getProperty(key);
        if (value == null
            || !value.isJsonPrimitive()
            || !value.getAsJsonPrimitive().isNumber()) {
            return def;
        }
        return value.getAsJsonPrimitive().getAsNumber();
    }

    /**
     * Get a boolean from the object
     *
     * @param def The default value to return if the property was not found.
     * @return boolean with the specified key, or def if the property was not found
     * @see #getProperty(String)
     */
    public boolean getBoolProperty(String key, boolean def) {
        JsonElement value = getProperty(key);
        if (value == null
            || !value.isJsonPrimitive()
            || !value.getAsJsonPrimitive().isBoolean()) {
            return def;
        }
        return value.getAsJsonPrimitive().getAsBoolean();
    }

    /**
     * Get a JsonArray property from the object
     *
     * @return JsonArray with the specified key, or null if no such JsonArray was found
     * @see #getProperty(String)
     */
    public JsonArray getArrayProperty(String key) {
        JsonElement result = getProperty(key);
        if (result == null || !result.isJsonArray()) {
            return null;
        }
        return result.getAsJsonArray();
    }

    /**
     * Get a JsonObject property from the object
     *
     * @return JsonObject with the specified key, or null if no such JsonObject was found
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
     * @return The value of the specified property, or null if it does not exist
     */
    public JsonElement getProperty(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Property key cannot be null");
        } else if (key.isEmpty()) {
            // Return root object if path is empty.
            return raw;
        }

        // Tokenize the key at un-escaped dots.
        // The negative-lookbehind ensures that dots are not preceded by a backslash.
        String[] tokens = key.split("(?<!\\\\)\\.");

        JsonObject parent = getRaw();
        for (int i = 0; i < tokens.length; i++) {

            JsonElement child = parent.get(tokens[i].replace("\\.", "."));
            if (child != null) {

                if (i < tokens.length - 1 && child.isJsonObject()) {
                    // The child was a json object & there's more to the path
                    parent = child.getAsJsonObject();

                } else if (i < tokens.length - 1) {
                    // We reached a value before the end of the path
                    return null;

                } else {
                    // We reached the end of the path, return the value
                    return child;
                }

            } else {
                // Some part of the path was set to null
                return null;
            }
        }

        return null;
    }
}
