package net.hypixel.api.reply.skyblock.bingo;

import net.hypixel.api.reply.AbstractReply;

import java.util.List;

public class SkyBlockBingoDataReply extends AbstractReply {
    private List<BingoEventData> events;

    public List<BingoEventData> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return "SkyBlockBingoPlayerDataReply{" +
                "events=" + events +
                "} " + super.toString();
    }
}
