package net.hypixel.api.reply;

import net.hypixel.api.util.Banner;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

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
        private Boolean publiclyListed;
        private Banner banner;
        private List<Member> members;
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

        public Boolean getPubliclyListed() {
            return publiclyListed;
        }

        public Banner getBanner() {
            return banner;
        }

        public List<Member> getMembers() {
            return members;
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
                    ", publiclyListed=" + publiclyListed +
                    ", banner=" + banner +
                    ", members=" + members +
                    ", coins=" + coins +
                    ", coinsEver=" + coinsEver +
                    ", created=" + created +
                    ", joinable=" + joinable +
                    ", exp=" + exp +
                    ", legacyRanking=" + legacyRanking +
                    '}';
        }

        public class Member {
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
    }
}
