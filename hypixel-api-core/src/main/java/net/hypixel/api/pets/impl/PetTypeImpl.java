package net.hypixel.api.pets.impl;

import com.google.gson.JsonObject;
import net.hypixel.api.pets.IPetRarity;
import net.hypixel.api.pets.IPetType;

public class PetTypeImpl implements IPetType {

    private final String key;
    private final String name;
    private final IPetRarity rarity;
    private final String typePackage;

    public PetTypeImpl(PetRepositoryImpl repository, JsonObject jsonObject) {
        this.key = jsonObject.get("key").getAsString();
        this.name = jsonObject.get("name").getAsString();
        this.rarity = jsonObject.get("rarity").isJsonNull() ? null : repository.getRarityByName(jsonObject.get("rarity").getAsString());
        this.typePackage = jsonObject.get("package").getAsString();
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IPetRarity getRarity() {
        return rarity;
    }

    @Override
    public String getPackage() {
        return typePackage;
    }

    @Override
    public String toString() {
        return "PetTypeImpl{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", rarity=" + rarity +
                ", typePackage='" + typePackage + '\'' +
                '}';
    }
}
