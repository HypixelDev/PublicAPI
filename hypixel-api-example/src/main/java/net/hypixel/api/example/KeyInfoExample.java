package net.hypixel.api.example;

public class KeyInfoExample {
    public static void main(String[] args) {
        ExampleUtil.API.getKey().whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
