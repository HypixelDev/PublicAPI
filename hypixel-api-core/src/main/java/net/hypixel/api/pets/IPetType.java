package net.hypixel.api.pets;

public interface IPetType {

    /**
     * @return the key of this pet type
     */
    String getKey();

    /**
     * @return the name of this pet type
     */
    String getName();

    /**
     * Note that the rarity can be {@code null}
     *
     * @return the rarity of this pet type
     */
    IPetRarity getRarity();

    /**
     * Note that the package is always {@code null} when using the backwards-compatible repository.
     *
     * @return the package of this pet type
     */
    String getPackage();

}
