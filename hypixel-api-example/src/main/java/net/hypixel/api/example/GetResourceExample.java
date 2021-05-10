package net.hypixel.api.example;

import net.hypixel.api.util.ResourceType;

public class GetResourceExample {
    public static void main(String[] args) {
        ExampleUtil.API.getResource(ResourceType.CHALLENGES).whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
