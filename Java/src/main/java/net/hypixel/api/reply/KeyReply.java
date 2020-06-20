package net.hypixel.api.reply;

import com.google.gson.annotations.SerializedName;
import java.util.UUID;

public class KeyReply extends AbstractReply {
    private Key record;

    public Key getRecord() {
        return record;
    }

    @Override
    public String toString() {
        return "KeyReply{" +
                "record=" + record +
                "} " + super.toString();
    }

    public class Key {
        private UUID key;
        @SerializedName("owner")
        private UUID ownerUuid;
        private int totalQueries;
        private int queriesInPastMin;

        public UUID getKey() {
            return key;
        }

        public UUID getOwnerUuid() {
            return ownerUuid;
        }

        public int getTotalQueries() {
            return totalQueries;
        }

        public int getQueriesInPastMin() {
            return queriesInPastMin;
        }

        @Override
        public String toString() {
            return "Key{" +
                    "key=" + key +
                    ", ownerUuid=" + ownerUuid +
                    ", totalQueries=" + totalQueries +
                    ", queriesInPastMin=" + queriesInPastMin +
                    '}';
        }
    }
}
