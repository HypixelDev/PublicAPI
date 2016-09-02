package net.hypixel.example;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.request.Request;
import net.hypixel.api.request.RequestBuilder;
import net.hypixel.api.request.RequestParam;
import net.hypixel.api.request.RequestType;

public class SyncGetPlayerExample {
    public static void main(String[] args) {
        HypixelAPI.getInstance().setApiKey(ExampleUtil.API_KEY);

        Request request = RequestBuilder.newBuilder(RequestType.PLAYER)
                .addParam(RequestParam.PLAYER_BY_UUID, ExampleUtil.UUIDList.HYPIXEL)
                .createRequest();
        System.out.println(HypixelAPI.getInstance().getSync(request));

        HypixelAPI.getInstance().finish();
    }
}
