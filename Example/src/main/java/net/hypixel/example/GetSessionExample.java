package net.hypixel.example;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.reply.SessionReply;
import net.hypixel.api.request.Request;
import net.hypixel.api.request.RequestBuilder;
import net.hypixel.api.request.RequestParam;
import net.hypixel.api.request.RequestType;
import net.hypixel.api.util.Callback;

public class GetSessionExample {
    public static void main(String[] args) {
        HypixelAPI.getInstance().setApiKey(ExampleUtil.API_KEY);

        Request request = RequestBuilder.newBuilder(RequestType.SESSION)
                .addParam(RequestParam.SESSION_BY_NAME, "AgentKid")
                .createRequest();
        HypixelAPI.getInstance().getAsync(request, new Callback<SessionReply>(SessionReply.class) {
            @Override
            public void callback(Throwable failCause, SessionReply result) {
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
