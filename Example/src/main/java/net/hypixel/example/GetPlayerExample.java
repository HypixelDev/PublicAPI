package net.hypixel.example;

import net.hypixel.api.reply.PlayerReply.Player;

public class GetPlayerExample {

    public static void main(String[] args) {
        ExampleUtil.API.getPlayerByUuid(ExampleUtil.HYPIXEL)
            .whenComplete((playerReply, throwable) -> {
                if (throwable != null) {
                    throwable.printStackTrace();
                    System.exit(0);
                    return;
                }

                Player player = playerReply.getPlayer();

                if (player.exists()) {
                    System.out.println(
                        "Name:             " + player.getName() + "\n"
                            + "UUID:             " + player.getUuid() + "\n"
                            + "Rank:             " + player.getHighestRank() + "\n"
                            + "Previous Names:   " + player.getArrayProperty("knownAliases") + "\n"
                            + "On Build Team:    " + player.isOnBuildTeam() + "\n"
                            + "Exp:              " + player.getNetworkExp() + "\n"
                            + "Level:            " + player.getNetworkLevel() + "\n"
                            + "Karma:            " + player.getKarma() + "\n"
                            + "Most Recent Game: " + player.getMostRecentGameType() + "\n"
                            + "MC Version:       " + player.getLastKnownMinecraftVersion() + "\n"
                            + "Pet Stats:        " + player.getPetStats() + "\n"
                            + "SkyWars Deaths:   " + player
                            .getIntProperty("stats.SkyWars.deaths", 0)
                            + "\n"
                            + "Raw:              " + player.getRaw());
                } else {
                    System.out.println("Player not found!");
                }
            });
        ExampleUtil.await();
    }
}
