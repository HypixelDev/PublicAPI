package net.hypixel.api.reply.skyblock;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hypixel.api.reply.AbstractReply;

public class SkyBlockSkillsReply extends AbstractReply {
    private JsonElement skills;

    public JsonObject getSkills() {
        if (skills == null || skills.isJsonNull()) {
            return null;
        } else {
            return skills.getAsJsonObject();
        }
    }

    @Override
    public String toString() {
        return "SkyBlockSkillsReply{" +
                "skills=" + skills +
                "} " + super.toString();
    }
}
