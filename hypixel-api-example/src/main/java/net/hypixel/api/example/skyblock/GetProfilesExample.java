package net.hypixel.api.example.skyblock;

import net.hypixel.api.example.ExampleUtil;

public class GetProfilesExample {
    public static void main(String[] args) {
        ExampleUtil.API.getSkyBlockProfiles(ExampleUtil.HYPIXEL).whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
