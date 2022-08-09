package net.hypixel.api.util;

import net.hypixel.api.pets.IPetRarity;

public enum Rarity implements IPetRarity {

    COMMON("GREEN"),
    RARE("BLUE"),
    EPIC("DARK_PURPLE"),
    LEGENDARY("GOLD"),
    ;

    private final String name;
    private final String color;

    Rarity(String color) {
        this.name = name();
        this.color = color;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColor() {
        return color;
    }
}
