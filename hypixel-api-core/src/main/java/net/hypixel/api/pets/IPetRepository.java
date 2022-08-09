package net.hypixel.api.pets;

import java.util.Collection;

public interface IPetRepository {

    IPetType getTypeByKey(String type);

    IPetType getTypeByPackage(String typePackage);

    Collection<IPetType> getTypesForRarity(IPetRarity rarity);

    IPetRarity getRarityByName(String name);

    Collection<IPetRarity> getRarities();

    Collection<IPetType> getTypes();

}
