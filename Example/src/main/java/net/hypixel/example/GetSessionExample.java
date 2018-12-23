package net.hypixel.example;

public class GetSessionExample {
    public static void main(String[] args) {
        // this will always be null because hypixel is staff account
        // see comment in SessionReply
        ExampleUtil.API.getSessionByUuid(ExampleUtil.HYPIXEL).whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
