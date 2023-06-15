package net.hypixel.api.pets;

import net.hypixel.api.pets.impl.compatibility.BackwardsCompatibilityPetRepositoryImpl;
import net.hypixel.api.reply.PlayerReply;

import java.util.HashMap;
import java.util.Map;

public class PetStats {

    private final Map<IPetType, Pet> petMap = new HashMap<>();

    @Deprecated
    public PetStats(Map<String, Map<String, Object>> petStats) {
        this(BackwardsCompatibilityPetRepositoryImpl.INSTANCE, petStats);
    }

    public PetStats(IPetRepository petRepository, Map<String, Map<String, Object>> petStats) {
        for (Map.Entry<String, Map<String, Object>> stringMapEntry : petStats.entrySet()) {
            try {
                petMap.put(petRepository.getTypeByKey(stringMapEntry.getKey()), new Pet(stringMapEntry.getValue()));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid pet! " + stringMapEntry.getKey());
            }
        }
    }

    /**
     * Gets a specific pet based on its pet type. Each player can only have one pet per type
     *
     * <b>Note:</b> If this returns {@code null}, it <b>doesn't</b> mean that the player hasn't unlocked the pet.
     * What it does mean though, is that the player hasn't given any attributes nor a name to the pet
     *
     * To check if a player has unlocked a pet, use {@link IPetRepository#hasPlayerUnlocked(IPetType, PlayerReply.Player)}
     *
     * @param type the pet type to retrieve
     * @return {@code null} if the player hasn't given a name and hasn't given any {@link PetAttribute},
     * otherwise the {@link Pet} instance
     */
    public Pet getPet(IPetType type) {
        return petMap.get(type);
    }

    /**
     * Lists all the pets the player have
     *
     * @return a map filled with all the pets the player have
     * @deprecated Use {@link #listAllPets()} instead
     */
    @Deprecated
    public Map<PetType, Pet> getAllPets() {
        Map<PetType, Pet> oldPets = new HashMap<>();

        for (Map.Entry<IPetType, Pet> entry : petMap.entrySet()) {
            if (!(entry.getKey() instanceof PetType)) {
                oldPets.clear();
                throw new IllegalStateException("Cannot use #getAllPets when using the new pet repository. Please use #listAllPets");
            }

            oldPets.put((PetType) entry.getKey(), entry.getValue());
        }

        return oldPets;
    }

    /**
     * Lists all the pets the player have
     *
     * @return a map filled with all the pets the player have
     */
    public Map<IPetType, Pet> listAllPets() {
        return petMap;
    }

    @Override
    public String toString() {
        return "PetStats{" +
                "petMap=" + petMap +
                '}';
    }
}
