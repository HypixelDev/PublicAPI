/* © 2017 Hypixel Inc. All Rights Reserved. */
package net.hypixel.api.reply;

import net.hypixel.api.request.RequestType;

import java.util.UUID;

@SuppressWarnings("unused")
public class KeyReply extends AbstractReply {
    private Key record;

    public Key getRecord() {
        return record;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.KEY;
    }

    @Override
    public String toString() {
        return "KeyReply{" +
                "record=" + record +
                ", super=" + super.toString() + "}";
    }

    public class Key {
        private UUID key;
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
