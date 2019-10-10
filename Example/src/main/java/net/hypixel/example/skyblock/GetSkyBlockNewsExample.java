package net.hypixel.example.skyblock;

import net.hypixel.example.ExampleUtil;

public class GetSkyBlockNewsExample {
    public static void main(String[] args) {
        ExampleUtil.API.getSkyBlockNews().whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
