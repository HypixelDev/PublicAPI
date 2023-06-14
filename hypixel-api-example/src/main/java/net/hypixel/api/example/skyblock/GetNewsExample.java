package net.hypixel.api.example.skyblock;

import net.hypixel.api.example.ExampleUtil;

public class GetNewsExample {
    public static void main(String[] args) {
        ExampleUtil.API.getSkyBlockNews().whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
