package net.hypixel.api.reply;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import net.hypixel.api.HypixelAPI;
import net.hypixel.api.pets.PetStats;
import net.hypixel.api.util.GameType;
import net.hypixel.api.util.ILeveling;
import net.hypixel.api.util.Utilities;

public class PlayerReply extends AbstractReply {

    private static final Player BLANK_PLAYER = new Player(null);

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

        private static final String DEFAULT_RANK = "NONE";

        private final JsonElement raw;

        /**
         * @param raw A JSON object representing a Hypixel player, as returned from the API
         */
        public Player(JsonElement raw) {
            this.raw = raw;
        }

        /**
         * @return The player's Minecraft UUID
         */
        public UUID getUuid() {
            String uuidStr = getStringProperty("uuid", null);
            return uuidStr != null ? Utilities.uuidFromString(uuidStr) : null;
        }

        /**
         * @return The Minecraft username the player had when they last connected to Hypixel
         */
        public String getName() {
            // Attempt to get their display name
            String displayName = getStringProperty("displayname", null);
            if (displayName != null) {
                return displayName;
            }

            // Fallback to their most recently-known alias
            JsonArray knownAliases = getArrayProperty("knownAliases");
            if (knownAliases != null && knownAliases.size() > 0) {
                return knownAliases.get(knownAliases.size() - 1).getAsString();
            }

            // Fallback to lowercase variants of their name
            return getStringProperty("playername", getStringProperty("username", null));
        }

        /**
         * @return The total amount of network experience earned by this player
         */
        public long getNetworkExp() {
            long exp = getLongProperty("networkExp", 0);
            exp += ILeveling.getTotalExpToFullLevel(getLongProperty("networkLevel", 0) + 1);
            return exp;
        }

        /**
         * @return The player's precise network level (including progress)
         */
        public double getNetworkLevel() {
            return ILeveling.getExactLevel(getNetworkExp());
        }

        /**
         * @return The total amount of karma points earned by this player
         */
        public long getKarma() {
            return getLongProperty("karma", 0);
        }

        /**
         * @return The date when the player first connected to Hypixel
         */
        public Date getFirstLoginDate() {
            return new Date(getLongProperty("firstLogin", 0));
        }

        /**
         * @return The date when the player most recently connected to Hypixel
         */
        public Date getLastLoginDate() {
            return new Date(getLongProperty("lastLogin", 0));
        }

        /**
         * @return The date when the player more recently disconnected from Hypixel
         */
        public Date getLastLogoutDate() {
            return new Date(getLongProperty("lastLogout", 0));
        }

        /**
         * @return Whether or not the player is currently connected to the Hypixel network
         * @see HypixelAPI#getStatus(UUID)
         * @deprecated The <code>status</code> endpoint ({@link HypixelAPI#getStatus(UUID)}) is
         * recommended for checking a player's online status
         */
        @Deprecated
        public boolean isOnline() {
            return getLongProperty("lastLogin", 0) > getLongProperty("lastLogout", 0);
        }

        /**
         * @return The color of the player's "+"s if they have MVP+ or MVP++. If they do not have
         * either rank, or if they have not selected a color, "RED" is returned as the default
         */
        public String getSelectedPlusColor() {
            return getStringProperty("rankPlusColor", "RED");
        }

        /**
         * @return The color of the player's tag if they have MVP++; defaults to "GOLD"
         */
        public String getSuperstarTagColor() {
            return getStringProperty("monthlyRankColor", "GOLD");
        }

        /**
         * The highest network rank that this player has; prefixes are not taken into consideration
         * <p>
         * Example: If... <ul>
         * <li>A player's base rank is MVP+</li>
         * <li>They have a subscription for MVP++</li>
         * <li>They are a staff member with the HELPER rank</li>
         * </ul>
         * ...then this method will return "HELPER".
         *
         * @return This player's highest network rank, or "NONE" if they do not have any
         * @see <a href=https://github.com/HypixelDev/PublicAPI/wiki/Common-Questions#how-do-i-get-a-players-rank-prefix>"How
         * do I get a player's rank prefix?"</a>
         */
        public String getHighestRank() {
            if (hasRankInField("rank")) {
                return getStringProperty("rank", DEFAULT_RANK);

            } else if (hasRankInField("monthlyPackageRank")) {
                return getStringProperty("monthlyPackageRank", DEFAULT_RANK);

            } else if (hasRankInField("newPackageRank")) {
                return getStringProperty("newPackageRank", DEFAULT_RANK);

            } else if (hasRankInField("packageRank")) {
                return getStringProperty("packageRank", DEFAULT_RANK);
            }

            return DEFAULT_RANK;
        }

        /**
         * @return Whether or not this user has a network rank (e.g. VIP, MVP++, MODERATOR, etc)
         */
        public boolean hasRank() {
            return !getHighestRank().equals(DEFAULT_RANK);
        }

        /**
         * Utility method for checking if a rank-related field contains a non-default rank (value is
         * not null, "NONE", or "NORMAL)
         *
         * @param name Name/dot-path of the field to check
         * @return Whether or not the field contains a non-default rank value
         */
        protected boolean hasRankInField(String name) {
            String value = getStringProperty(name, DEFAULT_RANK);
            return !value.isEmpty() && !value.equals("NONE") && !value.equals("NORMAL");
        }

        /**
         * @return Whether or not this player is a member of the <a href=https://twitter.com/hypixelbuilders>Hypixel
         * Build Team</a>
         */
        public boolean isOnBuildTeam() {
            return getBoolProperty("buildTeam", false)
                || getBoolProperty("buildTeamAdmin", false);
        }

        /**
         * @return The player's most recently played {@link GameType}, or null if it is unknown
         */
        public GameType getMostRecentGameType() {
            try {
                return GameType.valueOf(getStringProperty("mostRecentGameType", ""));
            } catch (IllegalArgumentException ignored) {
                return null;
            }
        }

        /**
         * @return The player's pet stats, or null if they have none
         */
        public PetStats getPetStats() {
            JsonObject petStats = getObjectProperty("petStats");
            if (petStats == null) {
                return null;
            }

            Type statsObjectType = new TypeToken<Map<String, Map<String, Object>>>() {
            }.getType();
            return new PetStats(Utilities.GSON.fromJson(petStats, statsObjectType));
        }

        /**
         * @return The last Minecraft version that the player used to connect to Hypixel, or null if
         * it is unknown
         */
        public String getLastKnownMinecraftVersion() {
            return getStringProperty("mcVersionRp", null);
        }

        /**
         * @return The raw player object returned by the Hypixel API
         */
        public JsonObject getRaw() {
            if (raw == null || !raw.isJsonObject()) {
                return null;
            } else {
                return raw.getAsJsonObject();
            }
        }

        /**
         * @return Whether or not the API returned null for this player
         */
        public boolean exists() {
            return raw != null && raw.isJsonObject();
        }

        /**
         * Get a String from the player object
         *
         * @param key
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
         * Get a float from the player object
         *
         * @param key
         * @param def The default value to return if the property was not found
         * @return float with the specified key, or def if the property was not found
         * @see #getProperty(String)
         */
        public float getFloatProperty(String key, float def) {
            return getNumberProperty(key, def).floatValue();
        }

        /**
         * Get a double from the player object
         *
         * @param key
         * @param def The default value to return if the property was not found
         * @return double with the specified key, or def if the property was not found
         * @see #getProperty(String)
         */
        public double getDoubleProperty(String key, double def) {
            return getNumberProperty(key, def).doubleValue();
        }

        /**
         * Get a long from the player object
         *
         * @param key
         * @param def The default value to return if the property was not found
         * @return long with the specified key, or def if the property was not found
         * @see #getProperty(String)
         */
        public long getLongProperty(String key, long def) {
            return getNumberProperty(key, def).longValue();
        }

        /**
         * Get an integer from the player object
         *
         * @param key
         * @param def The default value to return if the property was not found
         * @return int with the specified key, or def if the property was not found
         * @see #getProperty(String)
         */
        public int getIntProperty(String key, int def) {
            return getNumberProperty(key, def).intValue();
        }

        /**
         * Get a Number property from the player object
         *
         * @param key
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
         * Get a boolean from the player object
         *
         * @param key
         * @param def The default value to return if the property was not found
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
         * Get a JsonArray property from the player object
         *
         * @param key
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
         * Get a JsonObject property from the player object
         *
         * @param key
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
         * Read a property from the player object returned by the API
         *
         * @param path Dot-notation path to the desired field (ie "stats.SkyWars.deaths" to get the
         *             player's total deaths in SkyWars)
         * @return The value of the specified property, or null if it does not exist
         */
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

        /**
         * @param path Dot-notation path to the desired field (ie "stats.SkyWars" to check if the
         *             player has any SkyWars stats)
         * @return Whether or not the player has a property set at the given path, even if the value
         * is {@link com.google.gson.JsonNull}
         */
        public boolean hasProperty(String path) {
            return getProperty(path) != null;
        }

        @Override
        public String toString() {
            return "Player" + raw;
        }
    }
}
