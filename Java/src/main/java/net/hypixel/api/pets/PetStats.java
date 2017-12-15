package net.hypixel.api.pets;

import com.google.common.collect.Maps;

import java.util.Map;

public class PetStats {

    private Map<PetType, Pet> petMap = Maps.newHashMap();

    public PetStats(Map<String, Map<String, Object>> petStats) {
        for (Map.Entry<String, Map<String, Object>> stringMapEntry : petStats.entrySet()) {
            try {
                petMap.put(PetType.valueOf(stringMapEntry.getKey()), new Pet(stringMapEntry.getValue()));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid pet! " + stringMapEntry.getKey());
            }
        }
    }

    public Pet getPet(PetType type) {
        return petMap.get(type);
    }

    public Map<PetType, Pet> getAllPets() {
        return petMap;
    }

    @Override
    public String toString() {
        return "PetStats{" +
                "petMap=" + petMap +
                '}';
    }
}
