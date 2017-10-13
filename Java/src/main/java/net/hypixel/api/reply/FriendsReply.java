/* © 2017 Hypixel Inc. All Rights Reserved. */
package net.hypixel.api.reply;

import net.hypixel.api.request.RequestType;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public class FriendsReply extends AbstractReply {
    private List<FriendShip> records;

    public List<FriendShip> getFriendShips() {
        return records;
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.FRIENDS;
    }

    @Override
    public String toString() {
        return "FriendsReply{" +
                "friendShips=" + records +
                ", super=" + super.toString() + "}";
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
