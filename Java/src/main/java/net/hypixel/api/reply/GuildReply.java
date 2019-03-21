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
        private int memberSizeLevel;
        private int bankSizeLevel;
        private Boolean canTag;
        private Boolean canParty;
        private Boolean canMotd;
        private int legacyRanking;
        private List<Rank> ranks;

        /**
         * @see #addRanksToMembers()
         */
        private boolean isRankToMembersAdded = false;

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
            addRanksToMembers();

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

        public int getMemberSizeLevel() {
            return memberSizeLevel;
        }

        public int getBankSizeLevel() {
            return bankSizeLevel;
        }

        public Boolean getCanTag() {
            return canTag;
        }

        public Boolean getCanParty() {
            return canParty;
        }

        public Boolean getCanMotd() {
            return canMotd;
        }

        public int getLegacyRanking() {
            return legacyRanking;
        }

        public List<Rank> getRanks() {
            return ranks;
        }

        @Override
        public String toString() {
            addRanksToMembers();

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
                    ", memberSizeLevel=" + memberSizeLevel +
                    ", bankSizeLevel=" + bankSizeLevel +
                    ", canTag=" + canTag +
                    ", canParty=" + canParty +
                    ", canMotd=" + canMotd +
                    ", legacyRanking=" + legacyRanking +
                    ", ranks=" + ranks +
                    '}';
        }

        private void addRanksToMembers() {
            if (!isRankToMembersAdded) {

                // GuildMaster rank is not included in {@link #ranks} list from hypixel network
                // So, we must add it
                Rank guildMasterRank = new Rank();
                guildMasterRank.name = "GUILDMASTER";
                guildMasterRank.created = getCreated(); // the rank is created when the guild is created?
                guildMasterRank.priority = 10; // must be higher than all the other ranks (max 5)
                ranks.add(guildMasterRank);

                // associate the members to their rank

                // Ranks names are saved either as "member", "Member" or "MEMBER"
                // So we need to lowercase all the ranks names, THEN compare them
                for (Rank rank : getRanks()) {
                    rank.lower_name = rank.name.toLowerCase();
                }

                for (Member member : members) {
                    String memberRank = member.rank.toLowerCase();

                    for (Rank rank : getRanks()) {
                        if (memberRank.equals(rank.lower_name)) {
                            member.rankInstance = rank;
                        }
                    }
                }

                isRankToMembersAdded = true;
            }
        }

        public class Member {
            private UUID uuid;
            private String rank;
            private ZonedDateTime joined;

            private Rank rankInstance;

            public UUID getUuid() {
                return uuid;
            }

            public Rank getRank() {
                return rankInstance;
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

        public class Rank {
            private String name;
            private boolean default_;
            private String tag;
            private ZonedDateTime created;
            private byte priority;

            private String lower_name;

            public String getName() {
                return name;
            }

            public boolean getDefault() {
                return default_;
            }

            public byte getPriority() {
                return priority;
            }

            public String getTag() {
                return tag;
            }

            public ZonedDateTime getCreated() {
                return created;
            }

            @Override
            public String toString() {
                return "Rank{" +
                        "name=" + name +
                        ", default=" + default_ +
                        ", tag=" + tag +
                        ", created=" + created +
                        ", priority" + priority +
                        '}';
            }
        }
    }
}
