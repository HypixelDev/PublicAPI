package net.hypixel.example;

public class WatchdogStatsExample {
    public static void main(String[] args) {
        ExampleUtil.API.getWatchdogStats().whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
