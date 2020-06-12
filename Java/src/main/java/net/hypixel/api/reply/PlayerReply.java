package net.hypixel.api.reply;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PlayerReply extends AbstractReply {

    private static final Player BLANK_PLAYER = new Player();

    private Player player;

    public Player getPlayer() {
        return player == null ? BLANK_PLAYER : player;
    }

    @Override
    public String toString() {
        return "PlayerReply{" +
            "player=" + player +
            "} " + super.toString();
    }

    public static class Player {

        private JsonElement raw;

        public JsonObject getRaw() {
            if (raw == null || !raw.isJsonObject()) {
                return null;
            } else {
                return raw.getAsJsonObject();
            }
        }

        public boolean exists() {
            return raw != null && raw.isJsonObject();
        }

        public String getStringProperty(String key, String def) {
            JsonElement value = getProperty(key);
            if (value == null
                || !value.isJsonPrimitive()
                || !value.getAsJsonPrimitive().isString()) {
                return def;
            }
            return value.getAsJsonPrimitive().getAsString();
        }

        public float getFloatProperty(String key, float def) {
            return getNumberProperty(key, def).floatValue();
        }

        public double getDoubleProperty(String key, double def) {
            return getNumberProperty(key, def).doubleValue();
        }

        public long getLongProperty(String key, long def) {
            return getNumberProperty(key, def).longValue();
        }

        public int getIntProperty(String key, int def) {
            return getNumberProperty(key, def).intValue();
        }

        public Number getNumberProperty(String key, Number def) {
            JsonElement value = getProperty(key);
            if (value == null
                || !value.isJsonPrimitive()
                || !value.getAsJsonPrimitive().isNumber()) {
                return def;
            }
            return value.getAsJsonPrimitive().getAsNumber();
        }

        public boolean getBoolProperty(String key, boolean def) {
            JsonElement value = getProperty(key);
            if (value == null
                || !value.isJsonPrimitive()
                || !value.getAsJsonPrimitive().isBoolean()) {
                return def;
            }
            return value.getAsJsonPrimitive().getAsBoolean();
        }

        public JsonArray getArrayProperty(String key) {
            JsonElement result = getProperty(key);
            if (result == null || !result.isJsonArray()) {
                return null;
            }
            return result.getAsJsonArray();
        }

        public JsonObject getObjectProperty(String key) {
            JsonElement result = getProperty(key);
            if (result == null || !result.isJsonObject()) {
                return null;
            }
            return result.getAsJsonObject();
        }

        public JsonElement getProperty(String path) {
            if (path.trim().isEmpty()) {
                return raw;

            } else if (raw == null) {
                return null;
            }

            String[] pathParts = path.split("\\.");

            JsonObject currentObj = getRaw();
            for (int i = 0; i < pathParts.length; i++) {

                JsonElement value = currentObj.get(pathParts[i]);
                if (value != null) {

                    if (i < pathParts.length - 1 && value.isJsonObject()) {
                        // The child was a json object & there's more to the path
                        currentObj = value.getAsJsonObject();

                    } else if (i < pathParts.length - 1) {
                        // We reached a value before the end of the path
                        return null;

                    } else {
                        // We reached the end of the path, return the value
                        return value;
                    }

                } else {
                    // Some part of the path was set to null
                    return null;
                }
            }

            return null;
        }

        @Override
        public String toString() {
            return "Player" + raw;
        }
    }
}
