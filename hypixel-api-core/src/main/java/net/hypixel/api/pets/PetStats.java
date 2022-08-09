package net.hypixel.api.pets;

import net.hypixel.api.pets.impl.compatibility.BackwardsCompatibilityPetRepositoryImpl;

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

    public Pet getPet(IPetType type) {
        return petMap.get(type);
    }

    public Map<IPetType, Pet> getAllPets() {
        return petMap;
    }

    @Override
    public String toString() {
        return "PetStats{" +
                "petMap=" + petMap +
                '}';
    }
}
