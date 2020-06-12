package net.hypixel.example.skyblock;

import com.google.gson.JsonElement;
import java.util.Map;
import net.hypixel.example.ExampleUtil;

public class GetSkyBlockProfileExample {
    public static void main(String[] args) {
        ExampleUtil.API.getPlayerByUuid(ExampleUtil.HYPIXEL).whenComplete((playerReply, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
                System.exit(0);
                return;
            }

            for (Map.Entry<String, JsonElement> profileEntry : playerReply.getPlayer()
                .getObjectProperty("stats.SkyBlock.profiles").entrySet()) {
                ExampleUtil.API.getSkyBlockProfile(profileEntry.getKey())
                    .whenComplete(ExampleUtil.getTestConsumer());
                break;
            }
        });
        ExampleUtil.await();
    }
}
