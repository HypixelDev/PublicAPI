package net.hypixel.api.pets;

public enum PetAttribute {

    HUNGER,
    THIRST,
    EXERCISE;

    public int getDecay() {
        return 1;
    }

}