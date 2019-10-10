package net.hypixel.example.skyblock;

import net.hypixel.example.ExampleUtil;

public class GetSkyBlockCollectionsExample {
    public static void main(String[] args) {
        ExampleUtil.API.getSkyBlockCollections().whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
