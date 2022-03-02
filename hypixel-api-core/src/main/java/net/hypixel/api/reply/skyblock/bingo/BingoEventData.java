package net.hypixel.api.reply.skyblock.bingo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BingoEventData {
    private int key;
    private int points;
    @SerializedName("completed_goals")
    private List<String> completedGoals;

    public int getKey() {
        return key;
    }

    public int getPoints() {
        return points;
    }

    public List<String> getCompletedGoals() {
        return completedGoals;
    }

    @Override
    public String toString() {
        return "BingoEventData{" +
                "key=" + key +
                ", points=" + points +
                ", completedGoals=" + completedGoals +
                '}';
    }
}
