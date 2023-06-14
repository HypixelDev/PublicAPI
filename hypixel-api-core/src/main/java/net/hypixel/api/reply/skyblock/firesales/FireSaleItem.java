package net.hypixel.api.reply.skyblock.firesales;

import com.google.gson.annotations.SerializedName;

import java.time.ZonedDateTime;

public class FireSaleItem {
    @SerializedName("item_id")
    private String itemId;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private int amount;
    private int price;

    public String getItemId() {
        return itemId;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "FireSaleItem{" +
                "itemId='" + itemId + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
