package net.hypixel.example;

public class GetStatusExample {
    public static void main(String[] args) {
        // online will always be false because hypixel is staff account
        // see comment in SessionReply
        ExampleUtil.API.getStatus(ExampleUtil.HYPIXEL).whenComplete(ExampleUtil.getTestConsumer());
        ExampleUtil.await();
    }
}
