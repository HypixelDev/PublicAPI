package net.hypixel.api.util;

public enum ResourceType {

    ACHIEVEMENTS("achievements"),
    CHALLENGES("challenges"),
    QUEST("quests"),
    GUILDS_ACHIEVEMENTS("guilds/achievements"),
    GUILDS_PERMISSION("guilds/permissions"),
    SKYBLOCK_COLLECTIONS("skyblock/collections"),
    SKYBLOCK_SKILLS("skyblock/skills"),
    GAMES("games"),
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
