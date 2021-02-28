package net.hypixel.example;

public class PunishmentStatsExample {
    public static void main(String[] args) {
        ExampleUtil.API.getPunishmentStats().whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
