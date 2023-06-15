package net.hypixel.api.pets;

import net.hypixel.api.reply.PlayerReply.Player;

import java.util.Collection;

public interface IPetRepository {

    /**
     * Gets the pet type associated with the provided key parameter
     * A pet type is an object holding the pet's key, name, rarity and package.
     *
     * @param key the key of the pet
     * @return {@code null} if no pet type matches the key, otherwise the pet type associated with that key
     */
    IPetType getTypeByKey(String key);

    /**
     * Gets the pet type associated with the provided package parameter
     * A pet type is an object holding the pet's key, name, rarity and package.
     *
     * @param typePackage the package of the pet
     * @return {@code null} if using
     * {@link net.hypixel.api.pets.impl.compatibility.BackwardsCompatibilityPetRepositoryImpl} or if no pet type
     * matches the package, otherwise the pet type associated with that package
     */
    IPetType getTypeByPackage(String typePackage);

    /**
     * Lists all pets matching the given {@link IPetRarity}
     *
     * @param rarity The rarity of the pets
     * @return A collection (usually a {@link java.util.Set}) that contains all matched pets. If no pets are found,
     * this returns an empty collection.
     */
    Collection<IPetType> getTypesForRarity(IPetRarity rarity);

    /**
     * Gets the pet rarity matching the provided name
     *
     * @param name the name of the rarity
     * @return {@code null} if no rarity matches the provided name, otherwise returns the matched rarity
     */
    IPetRarity getRarityByName(String name);

    /**
     * Gets if a player has unlocked the specified {@link IPetType}
     *
     * @param type the pet type the player must have
     * @param player the player to check against
     * @return {@code true} if the player has unlocked the pet, otherwise {@code false}
     */
    boolean hasPlayerUnlocked(IPetType type, Player player);

    /**
     * @return a collection of all the cached rarities
     */
    Collection<IPetRarity> getRarities();

    /**
     * @return a collection of all the cached pet types
     */
    Collection<IPetType> getTypes();

}
