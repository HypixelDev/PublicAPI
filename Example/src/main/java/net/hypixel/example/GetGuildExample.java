package net.hypixel.example;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.reply.GuildReply;
import net.hypixel.api.request.Request;
import net.hypixel.api.request.RequestBuilder;
import net.hypixel.api.request.RequestParam;
import net.hypixel.api.request.RequestType;
import net.hypixel.api.util.Callback;

public class GetGuildExample {
    public static void main(String[] args) {
        HypixelAPI.getInstance().setApiKey(ExampleUtil.API_KEY);

        Request request = RequestBuilder.newBuilder(RequestType.GUILD)
                .addParam(RequestParam.GUILD_BY_ID, "53790cd7ed505dab83dad144")
                .createRequest();
        HypixelAPI.getInstance().getAsync(request, (Callback<GuildReply>) (failCause, result) -> {
            if (failCause != null) {
                failCause.printStackTrace();
            } else {
                 System.out.println(result);
            }
            HypixelAPI.getInstance().finish();
            System.exit(0);
        });
        ExampleUtil.await(); // This is required because the API is asynchronous, so without this the program will exit.
    }
}
