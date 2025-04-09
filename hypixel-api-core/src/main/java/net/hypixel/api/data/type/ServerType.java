package net.hypixel.api.data.type;

public interface ServerType {

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

    String name();

    String getName();

}
