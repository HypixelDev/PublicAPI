package net.hypixel.api.util.pets;

public enum PetAttribute {

    HUNGER,
    THIRST,
    EXERCISE;

    public int getDecay() {
        return 1;
    }

}