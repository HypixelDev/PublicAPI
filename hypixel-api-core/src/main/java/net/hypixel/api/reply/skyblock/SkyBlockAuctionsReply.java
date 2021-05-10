package net.hypixel.api.reply.skyblock;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.hypixel.api.reply.AbstractReply;

public class SkyBlockAuctionsReply extends AbstractReply {
    private int page;
    private int totalPages;
    private int totalAuctions;
    private long lastUpdated;
    private JsonElement auctions;

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalAuctions() {
        return totalAuctions;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public JsonArray getAuctions() {
        if (auctions == null || auctions.isJsonNull()) {
            return null;
        } else {
            return auctions.getAsJsonArray();
        }
    }

    public boolean hasNextPage() {
        return page < totalPages - 1;
    }

    public boolean hasPrevPage() {
        return page > 0;
    }

    @Override
    public String toString() {
        return "SkyBlockAuctionsReply{" +
                "page=" + page +
                ", totalPages=" + totalPages +
                ", totalAuctions=" + totalAuctions +
                ", lastUpdated=" + lastUpdated +
                ", auctions=" + auctions +
                "} " + super.toString();
    }
}
