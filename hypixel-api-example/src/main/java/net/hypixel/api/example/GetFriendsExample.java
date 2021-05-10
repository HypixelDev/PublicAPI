package net.hypixel.api.example;

public class GetFriendsExample {
    public static void main(String[] args) {
        ExampleUtil.API.getFriends(ExampleUtil.HYPIXEL).whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
