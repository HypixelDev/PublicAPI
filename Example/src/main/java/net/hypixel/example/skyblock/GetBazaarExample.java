package net.hypixel.example.skyblock;

import net.hypixel.example.ExampleUtil;

public class GetBazaarExample {
    public static void main(String[] args) {
        ExampleUtil.API.getBazaar().whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
