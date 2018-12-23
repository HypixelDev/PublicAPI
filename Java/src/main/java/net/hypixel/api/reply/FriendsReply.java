package net.hypixel.api.reply;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class FriendsReply extends AbstractReply {
    private List<FriendShip> records;

    public List<FriendShip> getFriendShips() {
        return records;
    }

    @Override
    public String toString() {
        return "FriendsReply{" +
                "records=" + records +
                "} " + super.toString();
    }

    public class FriendShip {

        private UUID uuidSender, uuidReceiver;
        private ZonedDateTime started;

        public UUID getUuidSender() {
            return uuidSender;
        }

        public UUID getUuidReceiver() {
            return uuidReceiver;
        }

        public ZonedDateTime getStarted() {
            return started;
        }

        @Override
        public String toString() {
            return "FriendShip{" +
                    "uuidSender=" + uuidSender +
                    ", uuidReceiver=" + uuidReceiver +
                    ", started=" + started +
                    '}';
        }
    }
}
