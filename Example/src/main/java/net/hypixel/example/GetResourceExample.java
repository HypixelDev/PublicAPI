package net.hypixel.example;

public class GetResourceExample {
    public static void main(String[] args) {
        ExampleUtil.API.getResource("achievements").whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
