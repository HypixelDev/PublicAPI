package net.hypixel.api.util;

@SuppressWarnings("unused")
public enum GameType {
    QUAKECRAFT("Quakecraft", 2),
    WALLS("Walls", 3),
    PAINTBALL("Paintball", 4),
    SURVIVAL_GAMES("Blitz Survival Games",5),
    TNTGAMES("The TNT Games", 6),
    VAMPIREZ("VampireZ", 7),
    WALLS3("Mega Walls", 13),
    ARCADE("Arcade", 14),
    ARENA("Arena Brawl", 17),
    MCGO("Cops and Crims", 21),
    UHC("UHC Champions", 20),
    BATTLEGROUND("Warlords", 23),
    TURBO_KART_RACERS("Turbo Kart Racers", 25);

    private static GameType[] v = values();
    private final String name;
    private final int id;

    private GameType(String name, int id) {
        this.name = name;
        this.id = id;
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

    /**
     * @param id The internal id
     * @return The GameType associated with that id, or null if there isn't one.
     */
    public static GameType fromId(int id) {
        for(GameType gameType : v) {
            if(gameType.id==id) {
                return gameType;
            }
        }
        return null;
    }

    /**
     * @param name The key used in the database
     * @return The GameType associated with that key, or null if there isn't one.
     */
    public static GameType fromDatabase(String name) {
        switch (name) {
            case "Quake":
                return QUAKECRAFT;
            case "Walls":
                return WALLS;
            case "Paintball":
                return PAINTBALL;
            case "HungerGames":
                return SURVIVAL_GAMES;
            case "TNTGames":
                return TNTGAMES;
            case "VampireZ":
                return VAMPIREZ;
            case "Walls3":
                return WALLS3;
            case "Arcade":
                return ARCADE;
            case "Arena":
                return ARENA;
            case "MCGO":
                return MCGO;
            case "UHC":
                return UHC;
            case "Battleground":
                return BATTLEGROUND;
        }
        return null;
    }
}
