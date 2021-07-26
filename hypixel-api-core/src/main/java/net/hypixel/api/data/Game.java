package net.hypixel.api.data;

import com.google.gson.JsonElement;
import net.hypixel.api.util.Utilities;

import java.util.Objects;

public class Game {
    private String key;
    private int id;
    private String name;
    private String databaseName;
    private boolean retired = false;
    private boolean legacy = false;

    public String getKey() {
        return key;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public boolean isRetired() {
        return retired;
    }

    public boolean isLegacy() {
        return legacy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Game{" +
                "key='" + key + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", databaseName='" + databaseName + '\'' +
                ", retired=" + retired +
                ", legacy=" + legacy +
                '}';
    }

    public static Game fromJson(String key, JsonElement data) {
        Game game = Utilities.GSON.fromJson(data, Game.class);
        game.key = key;
        return game;
    }
}

