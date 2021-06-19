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
import net.hypixel.api.util.UnstableHypixelObject;
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

    public static class Player extends UnstableHypixelObject {

        private static final String DEFAULT_RANK = "NONE";

        /**
         * @param raw A JSON object representing a Hypixel player, as returned from the API
         */
        public Player(JsonElement raw) {
            super(raw);
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
         * not null, "NONE", or "NORMAL")
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
         * @return Whether or not the API returned null for this player
         */
        public boolean exists() {
            return raw != null && raw.isJsonObject();
        }

        @Override
        public String toString() {
            return "Player" + raw;
        }
    }
}
