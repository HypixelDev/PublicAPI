package net.hypixel.example;

import net.hypixel.api.HypixelAPI;

import java.util.UUID;

public class SyncGetPlayerExample {
    public static void main(String[] args) {
        HypixelAPI.getInstance().setApiKey(UUID.fromString("64bd424e-ccb0-42ed-8b66-6e42a135afb4"));
        System.out.println(HypixelAPI.getInstance().getPlayerSync("AgentKid", null));
        HypixelAPI.getInstance().finish();
    }
}
