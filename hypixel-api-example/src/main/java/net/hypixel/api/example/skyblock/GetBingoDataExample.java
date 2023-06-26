package net.hypixel.api.example.skyblock;

import net.hypixel.api.example.ExampleUtil;

public class GetBingoDataExample {
    public static void main(String[] args) {
        ExampleUtil.API.getSkyblockBingoData(ExampleUtil.HYPIXEL).whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
