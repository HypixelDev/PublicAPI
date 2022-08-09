package net.hypixel.api.pets.impl;

import com.google.gson.JsonObject;
import net.hypixel.api.pets.IPetRarity;

public class PetRarityImpl implements IPetRarity {

    private final String name;
    private final String color;

    public PetRarityImpl(JsonObject jsonObject) {
        this.name = jsonObject.get("name").getAsString();
        this.color = jsonObject.get("color").getAsString();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "PetRarityImpl{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
