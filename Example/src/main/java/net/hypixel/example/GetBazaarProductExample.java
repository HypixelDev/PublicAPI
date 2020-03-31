package net.hypixel.example;

public class GetBazaarProductExample {
    public static void main(String[] args) {
        ExampleUtil.API.getBazaarProduct("ENCHANTED_GOLDEN_CARROT").whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
