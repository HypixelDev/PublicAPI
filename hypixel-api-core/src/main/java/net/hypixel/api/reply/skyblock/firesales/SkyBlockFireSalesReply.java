package net.hypixel.api.reply.skyblock.firesales;

import net.hypixel.api.reply.AbstractReply;

import java.util.List;

public class SkyBlockFireSalesReply extends AbstractReply {
    private List<FireSaleItem> sales;

    public List<FireSaleItem> getSales() {
        return sales;
    }

    @Override
    public String toString() {
        return "SkyBlockFireSalesReply{" +
                "sales=" + sales +
                "} " + super.toString();
    }
}
