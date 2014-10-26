package net.hypixel.example;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.reply.FindGuildReply;
import net.hypixel.api.util.Callback;

import java.util.UUID;

public class FindGuildExample {
    public static void main(String[] args) {
        HypixelAPI.getInstance().setApiKey(UUID.fromString("64bd424e-ccb0-42ed-8b66-6e42a135afb4"));
        HypixelAPI.getInstance().findGuild("AYS", null, new Callback<FindGuildReply>(FindGuildReply.class) {
            @Override
            public void callback(Throwable failCause, FindGuildReply result) {
                if (failCause != null) {
                    failCause.printStackTrace();
                } else {
                    System.out.println(result);
                }
                HypixelAPI.getInstance().finish();
            }
        });
    }
}
