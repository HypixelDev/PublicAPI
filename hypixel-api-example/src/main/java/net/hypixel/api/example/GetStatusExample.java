package net.hypixel.api.example;

import java.util.UUID;

public class GetStatusExample {
    public static void main(String[] args) {
        // online may vary from player to player, this is a setting that can be disabled by the player
        // see comment in StatusReply
        ExampleUtil.API.getStatus(UUID.fromString("ad8fefaa-8351-454b-b739-a4eaa872173f")).whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
