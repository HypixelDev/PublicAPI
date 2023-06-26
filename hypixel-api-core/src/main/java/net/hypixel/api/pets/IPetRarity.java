package net.hypixel.api.pets;

public interface IPetRarity {

    /**
     * Retrieves the name of this pet rarity.
     * This is only for pets and should not be mistaken with {@link net.hypixel.api.util.Rarity}.
     * Even though they currently are the same values, this might change in the future and should be used accordingly.
     *
     * @return the name of this rarity
     */
    String getName();

    /**
     * Retrieves the color of this pet rarity
     * This is only for pets and should not be mistaken with {@link net.hypixel.api.util.Rarity}.
     * Even though they currently are the same values, this might change in the future and should be used accordingly.
     *
     * @return the color of this rarity
     */
    String getColor();

}
