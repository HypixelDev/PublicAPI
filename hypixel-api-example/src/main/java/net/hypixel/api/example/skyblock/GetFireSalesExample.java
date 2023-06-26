package net.hypixel.api.example.skyblock;

import net.hypixel.api.example.ExampleUtil;

public class GetFireSalesExample {
    public static void main(String[] args) {
        ExampleUtil.API.getSkyBlockFireSales().whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
