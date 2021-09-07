package net.hypixel.api.reply;

import com.google.gson.annotations.SerializedName;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import net.hypixel.api.util.Banner;

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
        private String _id;

        private String name;
        private String description;
        private String tag;
        private String tagColor;
        private Boolean publiclyListed;
        private Banner banner;
        private List<Member> members;
        private List<Rank> ranks;
        private int coins;
        private int coinsEver;
        private ZonedDateTime created;
        private Boolean joinable;
        private long exp;
        private int legacyRanking;

        public String get_id() {
            return _id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getTag() {
            return tag;
        }

        public String getTagColor() {
            return tagColor;
        }

        public Boolean getPubliclyListed() {
            return publiclyListed;
        }

        public Banner getBanner() {
            return banner;
        }

        public List<Member> getMembers() {
            return members;
        }

        /**
         * Retrieves a list of permission groups created within the guild.
         *
         * @return the guild's ranks.
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
            return ranks;
        }

        public int getCoins() {
            return coins;
        }

        public int getCoinsEver() {
            return coinsEver;
        }

        public ZonedDateTime getCreated() {
            return created;
        }

        public Boolean getJoinable() {
            return joinable;
        }

        public long getExp() {
            return exp;
        }

        public int getLegacyRanking() {
            return legacyRanking;
        }

        @Override
        public String toString() {
            return "Guild{" +
                    "_id='" + _id + '\'' +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", tag='" + tag + '\'' +
                    ", tagColor='" + tagColor + '\'' +
                    ", publiclyListed=" + publiclyListed +
                    ", banner=" + banner +
                    ", members=" + members +
                    ", ranks=" + ranks +
                    ", coins=" + coins +
                    ", coinsEver=" + coinsEver +
                    ", created=" + created +
                    ", joinable=" + joinable +
                    ", exp=" + exp +
                    ", legacyRanking=" + legacyRanking +
                    '}';
        }

        public static class Member {
            private UUID uuid;
            private String rank;
            private ZonedDateTime joined;

            public UUID getUuid() {
                return uuid;
            }

            public String getRank() {
                return rank;
            }

            public ZonedDateTime getJoined() {
                return joined;
            }

            @Override
            public String toString() {
                return "Member{" +
                        "uuid=" + uuid +
                        ", rank=" + rank +
                        ", joined=" + joined +
                        '}';
            }
        }

        /**
         * A permission group that can be assigned to members of a Hypixel guild.
         */
        public static class Rank {
            private String name;
            private String tag;
            @SerializedName("default")
            private boolean isDefault;
            @SerializedName("created")
            private ZonedDateTime creationDate;
            private int priority;

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
        }
    }
}
