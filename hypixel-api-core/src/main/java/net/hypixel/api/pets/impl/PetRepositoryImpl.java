package net.hypixel.api.pets.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hypixel.api.pets.IPetRarity;
import net.hypixel.api.pets.IPetType;
import net.hypixel.api.reply.ResourceReply;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class PetRepositoryImpl extends AbstractPetRepositoryImpl {

    private final ResourceReply reply;

    private final Collection<IPetRarity> rarities;
    private final Collection<IPetType> types;

    public PetRepositoryImpl(ResourceReply reply) {
        this.reply = reply;

        if (!reply.isSuccess()) {
            throw new IllegalStateException("Cannot transform unsuccessful resource reply to pet repository");
        }

        this.rarities = parseCollection("rarities", PetRarityImpl::new);
        this.types = parseCollection("types", jsonObject -> new PetTypeImpl(this, jsonObject));
    }

    private <T> Collection<T> parseCollection(String key, Function<JsonObject, T> factory) {
        Set<T> set = new HashSet<>();

        JsonArray jsonArray = reply.getResponse().get(key).getAsJsonArray();

        for (JsonElement element : jsonArray) {
            if (!element.isJsonObject()) {
                throw new IllegalStateException("Invalid element in " + key + ": expected json object but got " + element);
            }

            set.add(factory.apply(element.getAsJsonObject()));
        }

        return Collections.unmodifiableSet(set);
    }

    @Override
    public IPetType getTypeByKey(String type) {
        for (IPetType petType : types) {
            if (petType.getKey().equals(type)) {
                return petType;
            }
        }

        return null;
    }

    @Override
    public IPetType getTypeByPackage(String typePackage) {
        for (IPetType petType : types) {
            if (petType.getPackage().equals(typePackage)) {
                return petType;
            }
        }

        return null;
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
        for (IPetRarity rarity : rarities) {
            if (rarity.getName().equals(name)) {
                return rarity;
            }
        }

        return null;
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
        return "PetRepositoryImpl{" +
                "rarities=" + rarities +
                ", types=" + types +
                '}';
    }

    public ResourceReply getReply() {
        return reply;
    }
}
