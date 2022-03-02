package net.hypixel.api.util;

public enum ResourceType {

    ACHIEVEMENTS("achievements"),
    CHALLENGES("challenges"),
    QUEST("quests"),
    GUILDS_ACHIEVEMENTS("guilds/achievements"),
    VANITY_PETS("vanity/pets"),
    VANITY_COMPANIONS("vanity/companions"),
    SKYBLOCK_COLLECTIONS("skyblock/collections"),
    SKYBLOCK_SKILLS("skyblock/skills"),
    SKYBLOCK_ITEMS("skyblock/items"),
    SKYBLOCK_ELECTION("skyblock/election"),
    SKYBLOCK_BINGO("skyblock/bingo"),
    ;

    /**
     * Path to resource
     */
    private final String path;

    ResourceType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
