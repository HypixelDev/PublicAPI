package net.hypixel.example.skyblock;

import net.hypixel.example.ExampleUtil;

public class GetSkyBlockProfilesExample {
    public static void main(String[] args) {
        ExampleUtil.API.getSkyBlockProfiles(ExampleUtil.HYPIXEL).whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
