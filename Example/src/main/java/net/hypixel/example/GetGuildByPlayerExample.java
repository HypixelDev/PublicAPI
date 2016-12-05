package net.hypixel.example;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.reply.FindGuildReply;
import net.hypixel.api.reply.GuildReply;
import net.hypixel.api.request.Request;
import net.hypixel.api.request.RequestBuilder;
import net.hypixel.api.request.RequestParam;
import net.hypixel.api.request.RequestType;
import net.hypixel.api.util.Callback;

public class GetGuildByPlayerExample {
    public static void main(String[] args) {
        HypixelAPI.getInstance().setApiKey(ExampleUtil.API_KEY);

        Request request = RequestBuilder.newBuilder(RequestType.FIND_GUILD)
                .addParam(RequestParam.GUILD_BY_PLAYER_UUID, ExampleUtil.UUIDList.HYPIXEL)
                .createRequest();
        HypixelAPI.getInstance().getAsync(request, (Callback<FindGuildReply>) (failCause, result) -> {
            if (failCause != null) {
                failCause.printStackTrace();
            } else {
                System.out.println(result);
                if (result.getGuild() == null) {
                    System.out.println("No guild by that name/player!");
                } else {
                    Request grequest = RequestBuilder.newBuilder(RequestType.GUILD)
                            .addParam(RequestParam.GUILD_BY_ID, result.getGuild())
                            .createRequest();
                    HypixelAPI.getInstance().getAsync(grequest, (Callback<GuildReply>) (gfailCause, gresult) -> {
                        if (gfailCause != null) {
                            gfailCause.printStackTrace();
                        } else {
                            System.out.println(gresult);
                        }
                    });
                    return;
                }
            }
            HypixelAPI.getInstance().finish();
            System.exit(0);
        });
        ExampleUtil.await(); // This is required because the API is asynchronous, so without this the program will exit.
    }
}
