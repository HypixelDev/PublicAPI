package net.hypixel.example.skyblock;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import net.hypixel.api.reply.skyblock.SkyBlockProfileReply;
import net.hypixel.example.ExampleUtil;

public class GetSkyBlockProfileExample {

    public static void main(String[] args) {
        ExampleUtil.API.getPlayerByUuid(ExampleUtil.HYPIXEL).whenComplete((reply, error) -> {
            if (error != null) {
                error.printStackTrace();
                System.exit(0);
                return;
            }

            // Get all of the player's profiles.
            JsonObject profiles = reply.getPlayer().getObjectProperty("stats.SkyBlock.profiles");
            if (profiles == null || profiles.entrySet().isEmpty()) {
                System.out.println("Player has no SkyBlock profiles");
                System.exit(0);
                return;
            }

            // Request each profile from the API & print the reply.
            Set<Entry<String, JsonElement>> profileEntries = profiles.entrySet();
            CompletableFuture<?>[] requests = new CompletableFuture<?>[profileEntries.size()];
            int i = 0;
            for (Entry<String, JsonElement> profile : profileEntries) {
                requests[i] = requestProfile(profile.getKey());
                i++;
            }

            // Only exit once all requests are completed.
            CompletableFuture.allOf(requests).whenComplete((ignored, profileError) -> {
                if (profileError != null) {
                    profileError.printStackTrace();
                }
                System.exit(0);
            });
        });
        ExampleUtil.await();
    }

    private static CompletableFuture<SkyBlockProfileReply> requestProfile(String profileId) {
        return ExampleUtil.API.getSkyBlockProfile(profileId).whenComplete((profileReply, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }

            System.out.println(profileReply);
        });
    }
}
