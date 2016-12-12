package net.hypixel.api.reply;

import com.google.common.collect.Maps;
import net.hypixel.api.request.RequestType;
import net.hypixel.api.util.Banner;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("unused")
public class GuildReply extends AbstractReply {
    private Guild guild;

    public Guild getGuild() {
        return guild;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.GUILD;
    }

    @Override
    public String toString() {
        return "GuildReply{" +
                "guild=" + guild +
                ", super=" + super.toString() + "}";
    }

    /**
     * Mainly used to identify classes
     */
    public interface GuildCoinHistoryHolding {
        Guild.GuildCoinHistory getGuildCoinHistory();
    }

    public static class Guild implements GuildCoinHistoryHolding {
        private String _id;

        private String name;
        private String tag;
        private Boolean publiclyListed;
        private Banner banner;
        private List<Member> members;
        private GuildCoinHistory guildCoinHistory;
        private int coins;
        private int coinsEver;
        private DateTime created;
        private Boolean joinable;
        private int memberSizeLevel;
        private int bankSizeLevel;
        private Boolean canTag;
        private Boolean canParty;
        private Boolean canMotd;

        public String get_id() {
            return _id;
        }

        public String getName() {
            return name;
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

        public GuildCoinHistory getGuildCoinHistory() {
            return guildCoinHistory;
        }

        public int getCoins() {
            return coins;
        }

        public int getCoinsEver() {
            return coinsEver;
        }

        public DateTime getCreated() {
            return created;
        }

        public Boolean getJoinable() {
            return joinable;
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

        @Override
        public String toString() {
            return "Guild{" +
                    "_id='" + _id + '\'' +
                    ", name='" + name + '\'' +
                    ", tag='" + tag + '\'' +
                    ", publiclyListed=" + publiclyListed +
                    ", banner=" + banner +
                    ", members=" + members +
                    ", guildCoinHistory=" + guildCoinHistory +
                    ", coins=" + coins +
                    ", coinsEver=" + coinsEver +
                    ", created=" + created +
                    ", joinable=" + joinable +
                    ", memberSizeLevel=" + memberSizeLevel +
                    ", bankSizeLevel=" + bankSizeLevel +
                    ", canTag=" + canTag +
                    ", canParty=" + canParty +
                    ", canMotd=" + canMotd +
                    '}';
        }

        public enum GuildRank {
            GUILDMASTER, OFFICER, MEMBER
        }

        public static class GuildCoinHistory {

            private Map<DateTime, Integer> coinHistory = Maps.newHashMap();

            public Map<DateTime, Integer> getCoinHistory() {
                return coinHistory;
            }

            @Override
            public String toString() {
                return "GuildCoinHistory{" +
                        "coinHistory=" + coinHistory +
                        '}';
            }
        }

        public class Member implements GuildCoinHistoryHolding {
            private UUID uuid;
            private GuildRank rank;
            private DateTime joined;
            private GuildCoinHistory guildCoinHistory;

            public UUID getUuid() {
                return uuid;
            }

            public GuildRank getRank() {
                return rank;
            }

            public DateTime getJoined() {
                return joined;
            }

            public GuildCoinHistory getGuildCoinHistory() {
                return guildCoinHistory;
            }

            @Override
            public String toString() {
                return "Member{" +
                        "uuid=" + uuid +
                        ", rank=" + rank +
                        ", joined=" + joined +
                        ", guildCoinHistory=" + guildCoinHistory +
                        '}';
            }
        }
    }
}
