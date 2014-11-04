package net.hypixel.example;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.reply.GuildReply;
import net.hypixel.api.reply.PlayerReply;
import net.hypixel.api.util.Callback;

import java.util.UUID;

public class GetGuildExample {
    public static void main(String[] args) {
        HypixelAPI.getInstance().setApiKey(UUID.fromString("64bd424e-ccb0-42ed-8b66-6e42a135afb4"));
        HypixelAPI.getInstance().getGuild("53790cd7ed505dab83dad144", new Callback<GuildReply>(GuildReply.class) {
            @Override
            public void callback(Throwable failCause, GuildReply result) {
                if (failCause != null) {
                    failCause.printStackTrace();
                } else {
                    System.out.println(result);
                }
                HypixelAPI.getInstance().finish();
                System.exit(0);
            }
        });
        ExampleUtil.await(); // This is required because the API is asynchronous, so without this the program will exit.
    }
}
