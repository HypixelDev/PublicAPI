package net.hypixel.api.util;

public enum GameType {
    QUAKECRAFT("Quakecraft", "Quake", 2),
    WALLS("Walls", "Walls", 3),
    PAINTBALL("Paintball", "Paintball", 4),
    SURVIVAL_GAMES("Blitz Survival Games", "HungerGames", 5),
    TNTGAMES("The TNT Games", "TNTGames", 6),
    VAMPIREZ("VampireZ", "VampireZ", 7),
    WALLS3("Mega Walls", "Walls3", 13),
    ARCADE("Arcade", "Arcade", 14),
    ARENA("Arena Brawl", "Arena", 17),
    MCGO("Cops and Crims", "MCGO", 21),
    UHC("UHC Champions", "UHC", 20),
    BATTLEGROUND("Warlords", "Battleground", 23),
    SUPER_SMASH("Smash Heroes", "SuperSmash", 24),
    GINGERBREAD("Turbo Kart Racers", "GingerBread", 25),
    HOUSING("Housing", "Housing", 26),
    SKYWARS("SkyWars", "SkyWars", 51),
    TRUE_COMBAT("Crazy Walls", "TrueCombat", 52),
    SPEED_UHC("Speed UHC", "SpeedUHC", 54),
    SKYCLASH("SkyClash", "SkyClash", 55),
    LEGACY("Classic Games", "Legacy", 56),
    PROTOTYPE("Prototype", "Prototype", 57),
    BEDWARS("Bed Wars", "Bedwars", 58),
    MURDER_MYSTERY("Murder Mystery", "MurderMystery", 59),
    BUILD_BATTLE("Build Battle", "BuildBattle", 60),
    DUELS("Duels", "Duels", 61),
    SKYBLOCK("SkyBlock", "SkyBlock", 63),
    PIT("Pit", "Pit", 64);

    private static final GameType[] VALUES = values();

    private final String name, dbName;
    private final int id;

    GameType(String name, String dbName, int id) {
        this.name = name;
        this.dbName = dbName;
        this.id = id;
    }

    /**
     * @param id The internal id
     * @return The GameType associated with that id, or null if there isn't one.
     */
    public static GameType fromId(int id) {
        for (GameType gameType : VALUES) {
            if (gameType.id == id) {
                return gameType;
            }
        }
        return null;
    }

    /**
     * @param dbName The key used in the database
     * @return The GameType associated with that key, or null if there isn't one.
     */
    public static GameType fromDatabase(String dbName) {
        for (GameType gameType : VALUES) {
            if (gameType.dbName.equals(dbName)) {
                return gameType;
            }
        }
        return null;
    }

    /**
     * Exposing this method allows people to use the array without cloning.
     * Slightly faster but not as safe since the array could be modified.
     */
    public static GameType[] getValues() {
        return VALUES;
    }

    /**
     * @return The official name of the GameType
     */
    public String getName() {
        return name;
    }

    /**
     * @return The internal ID that is occasionally used in various database schemas
     */
    public int getId() {
        return id;
    }

    public String getDbName() {
        return dbName;
    }
}
