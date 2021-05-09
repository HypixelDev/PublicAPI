package net.hypixel.api.example;

public class PunishmentStatsExample {
    public static void main(String[] args) {
        ExampleUtil.API.getPunishmentStats().whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
