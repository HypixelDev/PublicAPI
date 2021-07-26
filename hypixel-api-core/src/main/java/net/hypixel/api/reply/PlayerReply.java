package net.hypixel.api.reply;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import net.hypixel.api.HypixelAPI;
import net.hypixel.api.data.type.GameType;
import net.hypixel.api.pets.PetStats;
import net.hypixel.api.util.ILeveling;
import net.hypixel.api.util.UnstableHypixelObject;
import net.hypixel.api.util.Utilities;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

public class PlayerReply extends AbstractReply {

    // Suppressed because this field is dynamically assigned by Gson using reflection.
    @SuppressWarnings({"unused", "RedundantSuppression"})
    private Player player;

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "PlayerReply{" +
                "player=" + player +
                "} " + super.toString();
    }

    /**
     * Information and statistics for a player on the Hypixel network.
     * <p><br>
     * If the player does not {@link #exists() exist}, methods may return unexpected results.
     */
    public static class Player extends UnstableHypixelObject {

        private static final String DEFAULT_RANK = "NONE";

        /**
         * @param raw A JSON object representing a Hypixel player, as returned from the API. If this
         *            object is valid, it can be retrieved later via {@link #getRaw()}.
         */
        public Player(JsonElement raw) {
            super(raw);
        }

        /**
         * @return The player's Minecraft UUID, or {@code null} if the player does not {@link
         * #exists() exist}.
         */
        public UUID getUuid() {
            String uuidStr = getStringProperty("uuid", null);
            return uuidStr != null ? Utilities.uuidFromString(uuidStr) : null;
        }

        /**
         * @return The Minecraft username that the player had when they last connected to Hypixel.
         * {@code null} if the player's name is unknown.
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
         * @return The total amount of network experience earned by the player.
         */
        public long getNetworkExp() {
            long exp = getLongProperty("networkExp", 0);
            exp += ILeveling.getTotalExpToFullLevel(getLongProperty("networkLevel", 0) + 1);
            return exp;
        }

        /**
         * @return The player's precise network level, including their progress to the next level.
         */
        public double getNetworkLevel() {
            return ILeveling.getExactLevel(getNetworkExp());
        }

        /**
         * @return The total amount of karma points earned by the player.
         */
        public long getKarma() {
            return getLongProperty("karma", 0);
        }

        /**
         * @return The date when the player first connected to Hypixel. Defaults to the unix epoch
         * when unknown.
         */
        public ZonedDateTime getFirstLoginDate() {
            return getTimestamp("firstLogin");
        }

        /**
         * @return The last known time when the player connected to the main Hypixel network.
         * Defaults to the unix epoch when unknown.
         */
        public ZonedDateTime getLastLoginDate() {
            return getTimestamp("lastLogin");
        }

        /**
         * @return The last known time when the player disconnected from the main Hypixel network.
         * Defaults to the unix epoch when unknown.
         */
        public ZonedDateTime getLastLogoutDate() {
            return getTimestamp("lastLogout");
        }

        /**
         * @return {@code true} if the player is currently connected to the Hypixel network. {@code
         * false} otherwise, or if the player's online status is hidden in the API.
         * @see HypixelAPI#getStatus(UUID)
         * @deprecated The <code>status</code> endpoint ({@link HypixelAPI#getStatus(UUID)}) is
         * recommended for checking a player's online status.
         */
        @Deprecated
        public boolean isOnline() {
            return getLongProperty("lastLogin", 0) > getLongProperty("lastLogout", 0);
        }

        /**
         * @return The color of the player's "+"s if they have MVP+ or MVP++. If they do not have
         * either rank, or if they have not selected a color, {@code RED} is returned as the
         * default.
         */
        public String getSelectedPlusColor() {
            return getStringProperty("rankPlusColor", "RED");
        }

        /**
         * Note, returned colors use the names seen in <a href=https://minecraft.fandom.com/wiki/Formatting_codes#Color_codes>this
         * table</a>, in all uppercase. For example, {@code DARK_BLUE} and {@code GRAY}.
         *
         * @return The color of the player's name tag if they have MVP++. Defaults to {@code GOLD}.
         */
        public String getSuperstarTagColor() {
            return getStringProperty("monthlyRankColor", "GOLD");
        }

        /**
         * Returns the most privileged network rank that the player has.
         * <p><br>
         * Example: If... <ul>
         * <li>A player's base rank is MVP+ ({@code MVP_PLUS})</li>
         * <li>They have a subscription for MVP++ ({@code SUPERSTAR})</li>
         * <li>They are a staff member with the moderator rank ({@code MODERATOR})</li>
         * </ul>
         * ...then this method will return {@code MODERATOR}, because it has the highest permission
         * level of the three ranks.
         *
         * @return The most privileged network rank that the player has, or {@code NONE} if they do
         * not have any.
         * @apiNote Display prefixes are not considered, as they have no effect on permissions.
         * Examples include "OWNER" and "MOJANG".
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
         * @return {@code true} if the player has a network rank (e.g. {@code VIP}, {@code MVP++},
         * {@code MODERATOR}, etc).
         * @apiNote Display prefixes are not considered, as they are technically not ranks. Examples
         * include "OWNER" and "MOJANG".
         */
        public boolean hasRank() {
            return !getHighestRank().equals(DEFAULT_RANK);
        }

        /**
         * @return {@code true} if the player is a member of the <a href=https://twitter.com/hypixelbuilders>Hypixel
         * Build Team</a>. Otherwise {@code false}.
         */
        public boolean isOnBuildTeam() {
            return getBooleanProperty("buildTeam", false)
                    || getBooleanProperty("buildTeamAdmin", false);
        }

        /**
         * @return The player's most recently played {@link GameType}, or {@code null} if it is
         * unknown.
         */
        public GameType getMostRecentGameType() {
            try {
                return GameType.valueOf(getStringProperty("mostRecentGameType", ""));
            } catch (IllegalArgumentException ignored) {
                return null;
            }
        }

        /**
         * @return Information about the player's lobby pets, or {@code null} if they have none.
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
         * @return The last Minecraft version that the player used to connect to Hypixel, or {@code
         * null} if it is unknown.
         */
        public String getLastKnownMinecraftVersion() {
            return getStringProperty("mcVersionRp", null);
        }

        /**
         * @return {@code true} if the player could be identified by the API. Otherwise {@code
         * false}.
         */
        public boolean exists() {
            return getUuid() != null;
        }

        @Override
        public String toString() {
            return exists()
                    ? "Player" + raw
                    : "Player{exists=false}";
        }

        /**
         * Helper method for checking if a rank-related field contains a non-default rank.
         *
         * @param name The name/json-path of the field to check.
         * @return Whether or not the field contains a non-default rank value.
         * @implNote {@code false} if {@code null}, {@code NONE}, or {@code NORMAL}
         */
        protected boolean hasRankInField(String name) {
            String value = getStringProperty(name, DEFAULT_RANK);
            return !value.isEmpty() && !value.equals("NONE") && !value.equals("NORMAL");
        }

        /**
         * Helper method for deserializing unix timestamp fields, in milliseconds.
         *
         * @param name The name/json-path of the field to check.
         * @return The date represented by the timestamp, or the unix epoch if the field cannot be
         * found.
         */
        protected ZonedDateTime getTimestamp(String name) {
            long timestamp = getLongProperty(name, 0);
            return Utilities.getDateTime(timestamp);
        }
    }
}
