package net.hypixel.api.pets.impl.compatibility;

import net.hypixel.api.pets.IPetRarity;
import net.hypixel.api.pets.IPetType;
import net.hypixel.api.pets.PetType;
import net.hypixel.api.pets.impl.AbstractPetRepositoryImpl;
import net.hypixel.api.util.Rarity;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BackwardsCompatibilityPetRepositoryImpl extends AbstractPetRepositoryImpl {

    public static final BackwardsCompatibilityPetRepositoryImpl INSTANCE = new BackwardsCompatibilityPetRepositoryImpl();

    private final Collection<IPetRarity> rarities;
    private final Collection<IPetType> types;

    public BackwardsCompatibilityPetRepositoryImpl() {
        this.rarities = Arrays.asList(Rarity.values());
        this.types = Arrays.asList(PetType.VALUES);
    }

    @Override
    public IPetType getTypeByKey(String key) {
        for (IPetType petType : types) {
            if (petType.getKey().equals(key)) {
                return petType;
            }
        }

        return null;
    }

    @Override
    public IPetType getTypeByPackage(String typePackage) {
        return null; // Always return null, deprecated PetType enum doesn't include packages
    }

    @Override
    public Collection<IPetType> getTypesForRarity(IPetRarity rarity) {
        Set<IPetType> petTypes = new HashSet<>();

        for (IPetType petType : types) {
            if (petType.getRarity().equals(rarity)) {
                petTypes.add(petType);
            }
        }

        return petTypes;
    }

    @Override
    public IPetRarity getRarityByName(String name) {
        try {
            return Rarity.valueOf(name);
        } catch (IllegalArgumentException ignored) {
            return null;
        }
    }

    @Override
    public Collection<IPetRarity> getRarities() {
        return rarities;
    }

    @Override
    public Collection<IPetType> getTypes() {
        return types;
    }

    @Override
    public String toString() {
        return "BackwardsCompatibilityPetRepositoryImpl{" +
                "rarities=" + rarities +
                ", types=" + types +
                '}';
    }
}
