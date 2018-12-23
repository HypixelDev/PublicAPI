package net.hypixel.example;

public class FindGuildExample {
    public static void main(String[] args) {
        ExampleUtil.API.findGuildByPlayer(ExampleUtil.HYPIXEL).whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
