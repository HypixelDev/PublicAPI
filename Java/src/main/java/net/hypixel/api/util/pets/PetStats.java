package net.hypixel.api.util.pets;

import com.google.common.collect.Maps;

import java.util.Map;

public class PetStats {

    private Map<String, Pet> petMap = Maps.newHashMap(); // TODO add PetType enum

    public PetStats(Map<String, Map<String, Object>> petStats) {
        for (Map.Entry<String, Map<String, Object>> stringMapEntry : petStats.entrySet()) {
            petMap.put(stringMapEntry.getKey(), new Pet(stringMapEntry.getValue()));
        }
    }

    public Pet getPet(String type) {
        return petMap.get(type);
    }

    public Map<String, Pet> getAllPets() {
        return petMap;
    }
}
