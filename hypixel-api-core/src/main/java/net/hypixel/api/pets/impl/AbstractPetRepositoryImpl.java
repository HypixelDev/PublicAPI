package net.hypixel.api.pets.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.hypixel.api.pets.IPetRepository;
import net.hypixel.api.pets.IPetType;
import net.hypixel.api.pets.PetType;
import net.hypixel.api.reply.PlayerReply;

public abstract class AbstractPetRepositoryImpl implements IPetRepository {

    @Override
    public boolean hasPlayerUnlocked(IPetType type, PlayerReply.Player player) {
        if (type instanceof PetType) {
            throw new IllegalArgumentException("Old PetType enum doesn't include packages, which are required to use this method. Please use the new pet repository");
        }

        String packageName = type.getPackage();

        if (packageName == null) {
            throw new IllegalArgumentException("The provided pet type doesn't have a package, which is required to use this method");
        }

        JsonObject vanityMeta = player.getObjectProperty("vanityMeta");

        // Make sure vanityMeta is present as well as the inner packages array
        if (vanityMeta == null || !vanityMeta.has("packages")) {
            return false;
        }

        JsonElement packages = vanityMeta.get("packages");

        // Check if packages is an array
        if (!(packages instanceof JsonArray)) {
            return false;
        }

        JsonArray packagesArray = packages.getAsJsonArray();

        // Loop through packages until we find the pet type, if one matches
        for (JsonElement element : packagesArray) {
            // Make sure the element is a json primitive, so we can #getAsString without worry
            if (element instanceof JsonPrimitive) {
                if (element.getAsString().equalsIgnoreCase(packageName)) {
                    return true;
                }
            }
        }

        return false;
    }
}
