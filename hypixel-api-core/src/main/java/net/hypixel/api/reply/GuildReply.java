package net.hypixel.api.reply;

import com.google.gson.annotations.SerializedName;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import net.hypixel.api.data.type.GameType;
import net.hypixel.api.data.type.GuildAchievement;
import net.hypixel.api.reply.PlayerReply.Player;
import net.hypixel.api.util.Banner;

// Suppressed because most fields are assigned by Gson via reflection.
@SuppressWarnings({"unused", "RedundantSuppression", "MismatchedQueryAndUpdateOfCollection"})
public class GuildReply extends AbstractReply {

    private Guild guild;

    public Guild getGuild() {
        return guild;
    }

    @Override
    public String toString() {
        return "GuildReply{" +
               "guild=" + guild +
               "} " + super.toString();
    }

    public static class Guild {

        // Unclear/unconventionally named fields.
        @SerializedName("_id")
        private String id;
        @SerializedName("created")
        private ZonedDateTime creationDate;
        @SerializedName("exp")
        private long experience;
        @SerializedName("publiclyListed")
        private boolean isPubliclyListed;
        @SerializedName("joinable")
        private boolean isJoinable;

        // If any of these variables change names, make sure to include a @SerializedName("...").
        private String name;
        private String description;
        private String tag;
        private String tagColor;
        private Banner banner;
        private List<Member> members;
        private List<Rank> ranks;
        private List<GameType> preferredGames;
        private Map<GameType, Integer> guildExpByGameType;
        private Map<GuildAchievement, Integer> achievements;
        private int coins;
        private int coinsEver;
        private Integer legacyRanking; // Nullable so that non-ranked guilds don't appear as #1.

        /**
         * The unique BSON ObjectId that represents the guild. This should not change during the
         * guild's lifetime.
         *
         * @return the guild's main identifier.
         * @see <a href=https://docs.mongodb.com/manual/reference/bson-types/#objectid>ObjectId</a>
         */
        public String getId() {
            return id;
        }

        /**
         * @deprecated Renamed to {@link #getId()}.
         */
        @Deprecated
        public String get_id() {
            return getId();
        }

        /**
         * The guild's main display name. This is subject to change at any time.
         *
         * @return the guild's display name.
         */
        public String getName() {
            return name;
        }

        /**
         * A short, optional string set by a privileged member of the guild, and displayed in the
         * in-game guild finder.
         *
         * @return the guild's description, or {@code null} if none has been set.
         */
        public String getDescription() {
            return description;
        }

        /**
         * The date when the guild was created. If the guild has been disbanded and recreated in the
         * past, this is the date when the guild was most recently created.
         *
         * @return the date when the guild was created.
         */
        public ZonedDateTime getCreationDate() {
            return creationDate;
        }

        /**
         * @deprecated Renamed to {@link #getCreationDate()}.
         */
        @Deprecated
        public ZonedDateTime getCreated() {
            return getCreationDate();
        }

        /**
         * A short string displayed in-game after the names of members of the guild.
         *
         * @return the guild's tag, or {@code null} if the guild does not have one (or if it has not
         * been set).
         */
        public String getTag() {
            return tag;
        }

        /**
         * A Minecraft color code indicating the color of the guild's {@link #getTag() tag}. If this
         * returns {@code null} but the guild does have a tag, then the color is assumed to be
         * "{@code GRAY}" (without quotes).
         *
         * @return the name of a Minecraft color code (all uppercase), or {@code null} if the guild
         * has never changed its tag's color.
         * @see <a href=https://minecraft.fandom.com/wiki/Formatting_codes#Color_codes>Color codes
         * table</a> (uses lowercase names)
         */
        public String getTagColor() {
            return tagColor;
        }

        /**
         * The Minecraft-style banner displayed on the guild's hypixel.net website profile.
         *
         * @return the guild's banner, or {@code null} if none has been set.
         */
        public Banner getBanner() {
            return banner;
        }

        /**
         * Information about the Hypixel players who are currently members of the guild (at the time
         * the {@code Guild} object was fetched from the API).
         *
         * @return an immutable list of all of the guild's members.
         */
        public List<Member> getMembers() {
            return members == null
                ? Collections.emptyList()
                : Collections.unmodifiableList(members);
        }

        /**
         * A list of permission groups created within the guild.
         *
         * @return an immutable list of the guild's ranks. May be empty.
         * @apiNote This list may not be exhaustive, and typically only includes user-generated
         * ranks. Built-in ranks, and pre-guild-update ranks may not be included in this list,
         * despite being valid values for a member's {@link Member#getRank() rank} field. Extraneous
         * rank names include:
         * <ul>
         *     <li>{@code Guild Master} - Post-guild-update; full privileges</li>
         *     <li>{@code GUILDMASTER} - Pre-guild-update; full privileges</li>
         *     <li>{@code OFFICER} - Pre-guild-update; elevated privileges</li>
         *     <li>{@code MEMBER} - Pre-guild-update; normal privileges</li>
         * </ul>
         */
        public List<Rank> getRanks() {
            return ranks == null
                ? Collections.emptyList()
                : Collections.unmodifiableList(ranks);
        }

        /**
         * A list of games that can be used to search for the guild via the in-game guild finder.
         * Typically, this list represents the games that the guild considers their main focus.
         *
         * @return an immutable list of the guild's preferred games. May be empty.
         */
        public List<GameType> getPreferredGames() {
            return preferredGames == null
                ? Collections.emptyList()
                : Collections.unmodifiableList(preferredGames);
        }

        /**
         * Retrieves the total amount of {@link #getExperience() experience} that the guild has
         * earned from a particular game.
         *
         * @param game The game to retrieve experience for.
         * @return the amount of XP earned by the guild for the specified {@code game}.
         * @throws IllegalArgumentException if the provided {@code game} is {@code null}.
         */
        public int getExperienceForGame(GameType game) {
            if (game == null) {
                throw new IllegalArgumentException("Cannot get XP for null GameType");
            }

            return Optional.ofNullable(guildExpByGameType)
                .map(expByGame -> expByGame.get(game))
                .orElse(0);
        }

        /**
         * Retrieves the guild's high-score for a specific guild achievement. The meaning of "score"
         * varies between achievements, and can be found in the documentation for the desired {@link
         * GuildAchievement}.
         *
         * @param achievement the achievement to get the high-score of.
         * @return the guild's high-score for the provided achievement.
         */
        public int getAchievementHighScore(GuildAchievement achievement) {
            if (achievement == null) {
                throw new IllegalArgumentException("Cannot get high-score for null achievement");
            }
            return Optional.ofNullable(achievements)
                .map(highScores -> highScores.get(achievement))
                .orElse(0);
        }

        /**
         * The total amount of experience earned by the guild's members. This is different from a
         * player's {@link Player#getNetworkExp() network experience}, which is independent of the
         * guild system.
         *
         * @return the guild's total experience count.
         */
        public long getExperience() {
            return experience;
        }

        /**
         * @deprecated Renamed to {@link #getExperience()}.
         */
        @Deprecated
        public long getExp() {
            return getExperience();
        }

        /**
         * Whether or not the guild can be discovered via the in-game guild finder.
         *
         * @return whether or not the guild is listed publicly.
         */
        public boolean isPubliclyListed() {
            return isPubliclyListed;
        }

        /**
         * @deprecated Renamed to {@link #isPubliclyListed()}.
         */
        @Deprecated
        public Boolean getPubliclyListed() {
            return isPubliclyListed();
        }

        /**
         * Whether or not players can request to join the guild. If {@code false}, players must be
         * invited by a guild member with appropriate privileges.
         *
         * @return whether or not the guild can be joined without an invite.
         */
        public boolean isJoinable() {
            return isJoinable;
        }

        /**
         * @deprecated Renamed to {@link #isJoinable()}.
         */
        @Deprecated
        public Boolean getJoinable() {
            return isJoinable();
        }

        /**
         * The number of coins that the guild had prior to the 2018 guild update (when they were
         * replaced with {@link #getExperience() guild experience}). Coins were previously used to
         * purchase cosmetics for guilds (member slots, tags, etc), but now are only stored for
         * legacy purposes.
         *
         * @return the number of coins the guild has.
         */
        public int getCoins() {
            return coins;
        }

        /**
         * The total number of coins earned by the guild prior to the 2018 guild update. Unlike
         * {@link #getCoins()}, this number did not decrease when the guild purchased cosmetics
         * using coins.
         *
         * @return the number of coins earned during the guild's lifetime.
         * @see #getCoins()
         */
        public int getCoinsEver() {
            return coinsEver;
        }

        /**
         * The guild's ranking, out of all guilds, in terms of {@link #getCoins() coins} earned
         * before the 2018 guild update. The lowest place is {@code 1} (meaning 1st place) for
         * guilds that this applies to. For guilds created after the update, {@code -1} is
         * returned.
         *
         * @return the guild's position on the legacy coin leaderboard, or {@code -1} if the guild
         * was created after the guild update.
         * @see #getCoins()
         * @see #getCoinsEver()
         */
        public int getLegacyRanking() {
            return Optional.ofNullable(legacyRanking)
                .map(ranking -> ranking + 1)
                .orElse(-1);
        }

        @Override
        public String toString() {
            return "Guild{" +
                   "id='" + id + '\'' +
                   ", name='" + name + '\'' +
                   ", description='" + description + '\'' +
                   ", creationDate=" + creationDate +
                   ", tag='" + tag + '\'' +
                   ", tagColor='" + tagColor + '\'' +
                   ", banner=" + banner +
                   ", members=" + members +
                   ", ranks=" + ranks +
                   ", experience=" + experience +
                   ", isPubliclyListed=" + isPubliclyListed +
                   ", isJoinable=" + isJoinable +
                   ", coins=" + coins +
                   ", coinsEver=" + coinsEver +
                   ", legacyRanking=" + legacyRanking +
                   '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Guild guild = (Guild) o;
            return Objects.equals(id, guild.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        /**
         * Information about a Hypixel player pertaining to their guild.
         */
        public static class Member {

            private UUID uuid;
            private String rank;
            @SerializedName("joined")
            private ZonedDateTime joinDate;
            @SerializedName("expHistory")
            private Map<String, Integer> weeklyExperience;

            /**
             * The player's Minecraft identifier (version 4 UUID), assigned by Mojang.
             *
             * @return the player's UUID.
             */
            public UUID getUuid() {
                return uuid;
            }

            /**
             * The player's permission group in the guild. This rank name may not appear in the
             * guild's rank list; see {@link Guild#getRanks() here} for details.
             *
             * @return the player's guild rank.
             */
            public String getRank() {
                return rank;
            }

            /**
             * The date and time when the player joined the guild. If they have joined and left the
             * guild previously, this will be the date when they most recently re-joined.
             *
             * @return the date when the player joined their guild.
             */
            public ZonedDateTime getJoinDate() {
                return joinDate;
            }

            /**
             * Retrieves the amount of guild experience earned by the player on a particular date.
             * Daily XP information is typically only kept for one week, so results may not be found
             * for earlier dates.
             *
             * @param date The date to get the member's experience earnings for.
             * @return the amount of guild experience earned by the player on the {@code date}, or
             * {@code -1} if no total is kept for that day.
             */
            public int getExperienceEarned(LocalDate date) {
                if (date == null) {
                    throw new IllegalArgumentException("Cannot get XP for null date");
                }

                return Optional.ofNullable(weeklyExperience)
                    .map(expByDate -> expByDate.get(date.toString()))
                    .orElse(-1);
            }

            /**
             * @deprecated Renamed to {@link #getJoinDate()}.
             */
            @Deprecated
            public ZonedDateTime getJoined() {
                return getJoinDate();
            }

            @Override
            public String toString() {
                return "Member{" +
                       "uuid=" + uuid +
                       ", rank='" + rank + '\'' +
                       ", joined=" + joinDate +
                       '}';
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                Member member = (Member) o;
                return Objects.equals(uuid, member.uuid);
            }

            @Override
            public int hashCode() {
                return Objects.hash(uuid);
            }
        }

        /**
         * A permission group that can be assigned to members of a Hypixel guild.
         */
        public static class Rank {

            private String name;
            private String tag;
            private int priority;
            @SerializedName("default")
            private boolean isDefault;
            @SerializedName("created")
            private ZonedDateTime creationDate;

            /**
             * The rank's display name, as seen in guild chat and hypixel.net.
             *
             * @return the rank's name.
             */
            public String getName() {
                return name;
            }

            /**
             * A short string prefixed before the guild-chat messages of members with the rank.
             *
             * @return the rank's guild-chat prefix.
             */
            public String getChatTag() {
                return tag;
            }

            /**
             * Whether or not the rank is given to members initially upon joining the guild.
             *
             * @return a boolean indicating if the rank is the guild's default or not. If no rank in
             * the guild's list is marked with this, the default is the legacy "{@code MEMBER}" rank
             * (without quotes).
             */
            public boolean isDefault() {
                return isDefault;
            }

            /**
             * The date and time when the rank was added to to the guild.
             *
             * @return the rank's creation time.
             */
            public ZonedDateTime getCreationDate() {
                return creationDate;
            }

            /**
             * The rank's order in the guild's rank hierarchy. Members with appropriate permissions
             * cannot modify those of a rank with a higher priority than their own.
             *
             * @return the rank's permission priority.
             * @apiNote Higher Priority = Higher Privilege (and vice-versa)
             */
            public int getPriority() {
                return priority;
            }

            @Override
            public String toString() {
                return "Rank{" +
                       "name='" + name + '\'' +
                       ", tag='" + tag + '\'' +
                       ", isDefault=" + isDefault +
                       ", creationDate=" + creationDate +
                       ", priority=" + priority +
                       '}';
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                Rank rank = (Rank) o;
                return Objects.equals(name, rank.name) &&
                       Objects.equals(creationDate, rank.creationDate);
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, creationDate);
            }
        }
    }
}
