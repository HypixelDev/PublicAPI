package net.hypixel.api.example;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.pets.IPetRarity;
import net.hypixel.api.pets.IPetType;
import net.hypixel.api.pets.Pet;
import net.hypixel.api.pets.PetStats;
import net.hypixel.api.reply.PlayerReply;

import java.util.Map;

public class GetPetsExample {
    public static void main(String[] args) {
        HypixelAPI api = ExampleUtil.API;

        api.getPetRepository()
                .exceptionally(throwable -> {
                    throwable.printStackTrace();
                    System.exit(0);
                    return null;
                })
                .thenAccept(petRepository -> {
                    System.out.println("Fetched pet rarities:");
                    for (IPetRarity rarity : petRepository.getRarities()) {
                        System.out.println("\t" + rarity.getName());
                        System.out.println("\t\tColor: " + rarity.getColor());
                    }

                    System.out.println();
                    System.out.println("Fetched pet types:");

                    for (IPetType type : petRepository.getTypes()) {
                        System.out.println("\t" + type.getKey());
                        System.out.println("\t\tName:   " + type.getName());
                        System.out.println("\t\tRarity: " + type.getRarity());
                    }

                    System.out.println();

                    api.getPlayerByUuid(ExampleUtil.HYPIXEL)
                            .exceptionally(throwable -> {
                                throwable.printStackTrace();
                                System.exit(0);
                                return null;
                            })
                            .thenAccept(playerReply -> {
                                PlayerReply.Player player = playerReply.getPlayer();

                                if (!player.exists()) {
                                    System.err.println("Player not found!");
                                    System.exit(0);
                                    return;
                                }

                                PetStats petStats = player.getPetStats(petRepository);

                                System.out.println("Pet stats of \"" + player.getName() + "\":");

                                if (petStats == null) {
                                    System.out.println("No pet stats found for player.");
                                } else {
                                    for (Map.Entry<IPetType, Pet> entry : petStats.listAllPets().entrySet()) {
                                        System.out.println("\t" + entry.getKey().getKey() + ": " + entry.getValue().getLevel());
                                    }
                                }

                                IPetType catBlack = petRepository.getTypeByKey("CAT_BLACK");
                                IPetType blaze = petRepository.getTypeByKey("BLAZE");

                                System.out.println();
                                System.out.println("Does " + player.getName() + " have the " + catBlack.getName() +
                                        " pet? " + (petRepository.hasPlayerUnlocked(catBlack, player) ? "Yes." : "No."));
                                System.out.println("Does " + player.getName() + " have the " + blaze.getName() +
                                        " pet? " + (petRepository.hasPlayerUnlocked(blaze, player) ? "Yes." : "No."));

                                System.exit(0);
                            });
                });

        ExampleUtil.await();
    }
}
