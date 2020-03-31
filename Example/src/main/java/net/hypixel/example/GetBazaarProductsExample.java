package net.hypixel.example;

public class GetBazaarProductsExample {
    public static void main(String[] args) {
        ExampleUtil.API.getBazaarProducts().whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
