package net.hypixel.api.reply;

public class PlayerCountReply extends AbstractReply {
    private int playerCount;

    public int getPlayerCount() {
        return playerCount;
    }

    @Override
    public String toString() {
        return "PlayerCountReply{" +
                "playerCount=" + playerCount +
                "} " + super.toString();
    }
}
