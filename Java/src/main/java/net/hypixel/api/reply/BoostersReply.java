package net.hypixel.api.reply;

import net.hypixel.api.request.RequestType;
import net.hypixel.api.util.GameType;
import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public class BoostersReply extends AbstractReply {
    private List<Booster> boosters;

    public List<Booster> getBoosters() {
        return boosters;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.BOOSTERS;
    }

    @Override
    public String toString() {
        return "BoostersReply{" +
                "boosters=" + boosters +
                ", super=" + super.toString() + "}";
    }

    public class Booster {
        private UUID purchaserUuid;
        private int amount;
        private int originalLength;
        private int length;
        private GameType gameType;
        private DateTime dateActivated;

        public UUID getPurchaserUuid() {
            return purchaserUuid;
        }

        public int getAmount() {
            return amount;
        }

        public int getOriginalLength() {
            return originalLength;
        }

        public int getLength() {
            return length;
        }

        public GameType getGameType() {
            return gameType;
        }

        public DateTime getDateActivated() {
            return dateActivated;
        }

        @Override
        public String toString() {
            return "Booster{" +
                    "purchaserUuid=" + purchaserUuid +
                    ", amount=" + amount +
                    ", originalLength=" + originalLength +
                    ", length=" + length +
                    ", gameType=" + gameType +
                    ", dateActivated=" + dateActivated +
                    '}';
        }
    }
}
