package net.hypixel.example;

public class GameCountsExample {
    public static void main(String[] args) {
        ExampleUtil.API.getGameCounts().whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
