package net.hypixel.api.example;

public class GetLeaderboardsExample {
    public static void main(String[] args) {
        ExampleUtil.API.getLeaderboards().whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
