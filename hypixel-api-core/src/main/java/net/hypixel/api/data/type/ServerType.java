package net.hypixel.api.data.type;

public interface ServerType {

    String name();

    String getName();

    static ServerType valueOf(String value) {
        try {
            return GameType.valueOf(value);
        } catch (IllegalArgumentException e) {
            // ignored
        }

        try {
            return LobbyType.valueOf(value);
        } catch (IllegalArgumentException e) {
            // ignored
        }

        return null;
    }

}
