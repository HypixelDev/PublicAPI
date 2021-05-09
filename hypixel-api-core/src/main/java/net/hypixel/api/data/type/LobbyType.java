package net.hypixel.api.data.type;

public enum LobbyType implements ServerType {
    MAIN("Main Lobby"),
    TOURNAMENT("Tournament Hall"),
    ;

    private static final LobbyType[] VALUES = values();

    private final String name;

    LobbyType(String name) {
        this.name = name;
    }

    /**
     * Exposing this method allows people to use the array without cloning.
     * Slightly faster but not as safe since the array could be modified.
     */
    public static LobbyType[] getValues() {
        return VALUES;
    }

    @Override
    public String getName() {
        return name;
    }
}
