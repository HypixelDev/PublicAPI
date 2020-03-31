package net.hypixel.example;

public class GetStatusExample {
    public static void main(String[] args) {
        // online may vary from player to player, this is a setting that can be disabled by the player
        // see comment in StatusReply
        ExampleUtil.API.getStatus(ExampleUtil.HYPIXEL).whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
