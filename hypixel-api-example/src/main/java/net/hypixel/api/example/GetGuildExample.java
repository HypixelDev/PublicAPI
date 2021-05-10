package net.hypixel.api.example;

public class GetGuildExample {
    public static void main(String[] args) {
        ExampleUtil.API.getGuildByPlayer(ExampleUtil.HYPIXEL).whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
