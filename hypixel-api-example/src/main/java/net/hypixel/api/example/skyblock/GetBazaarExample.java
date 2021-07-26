package net.hypixel.api.example.skyblock;

import net.hypixel.api.example.ExampleUtil;

public class GetBazaarExample {
    public static void main(String[] args) {
        ExampleUtil.API.getSkyBlockBazaar().whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
