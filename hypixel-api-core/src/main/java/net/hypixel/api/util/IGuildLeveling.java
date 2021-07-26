package net.hypixel.api.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface IGuildLeveling {

    // TODO: 6/6/20 Finish javadocs and add examples

    /**
     * An unmodifiable list containing the experience needed to get from each level to the next. For
     * example, the value at index 0 is the amount of guild experience needed to progress from guild
     * level 0 to 1. The value at index 7 is the guild exp needed to progress from level 7 -> 8.
     * Etc.
     * <p>
     * The last element in this list is the value used for any levels beyond the size of this list.
     * For example, if this list has 15 values, then the last value is used for levels 14 -> 15 and
     * any levels after that.
     */
    List<Integer> EXP_NEEDED = Collections.unmodifiableList(Arrays.asList(
            100000, // Lvl 0 -> Lvl 1
            150000, // Lvl 1 -> Lvl 2
            250000, // Lvl 2 -> Lvl 3
            500000, // Etc
            750000,
            1000000,
            1250000,
            1500000,
            2000000,
            2500000,
            2500000,
            2500000,
            2500000,
            2500000,
            3000000
    ));

    /**
     * The last value in {@link #EXP_NEEDED}. This represents exp difference between any two levels
     * >= {@link #EXP_NEEDED}.size() - 1.
     *
     * @see #EXP_NEEDED
     */
    int MAX_EXP_NEEDED = EXP_NEEDED.get(EXP_NEEDED.size() - 1);

    /**
     * This method returns the full level of a guild with that amount of guild experience. This
     * method does not take into account the guild's progress to the next level, but will return an
     * integer representing the last whole guild level reached by the guild. If the experience
     * parameter is less than 0, an {@link IllegalArgumentException} will be thrown.
     *
     * @param exp The total experience gathered by a guild; should be >= 0
     * @return An integer representing the guild's current whole level
     */
    static double getLevel(double exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Experience value must be >= 0");
        }

        for (int level = 0; ; level++) {
            double needed = getExpFromLevelToNext(level);
            exp -= needed;

            if (exp < 0) {
                return level;
            }
        }
    }

    /**
     * This method returns the precise guild level for that amount of guild experience. This is the
     * equivalent of adding up the result of {@link #getLevel(double)} and {@link
     * #getPercentageToNextLevel(double)}. The value returned by this method is a floating point
     * number greater than or equal to 0, representing the guild's previse level. If the experience
     * parameter is less than 0, an {@link IllegalArgumentException} may be thrown.
     *
     * @param exp The total experience gathered by a guild; should be >= 0
     * @return Exact level of a guild with that much experience
     */
    static double getExactLevel(double exp) {
        return getLevel(exp) + getPercentageToNextLevel(exp);
    }

    /**
     * This method returns the amount of experience needed to go from that level to the next. If the
     * level parameter is less than 0, an {@link IllegalArgumentException} will be thrown.
     *
     * @param level The starting level
     * @return The amount of guild exp needed to progress from that level to the next level
     * @see #EXP_NEEDED
     * @see #MAX_EXP_NEEDED
     */
    static double getExpFromLevelToNext(double level) {
        if (level < 0) {
            throw new IllegalArgumentException("Level value must be >= 0");
        }

        return level >= EXP_NEEDED.size() ? MAX_EXP_NEEDED : EXP_NEEDED.get((int) level);
    }

    /**
     * This method returns the amount of guild experience needed to reach a precise level. For
     * example, passing in a level of 10.5 will return the amount of exp needed for level 10 plus
     * half the amount of exp needed between levels 10 and 11. If the level parameter is less than
     * 0, an {@link IllegalArgumentException} may be thrown.
     *
     * @param level The precise level reached with the returned amount of experience; should be >=
     *              0
     * @return The total experience needed to reach that precise level
     */
    static double getTotalExpToLevel(double level) {
        double progress = level - (int) level;
        return getTotalExpToFullLevel(level) + (progress * getExpFromLevelToNext(level));
    }

    /**
     * This method returns the total amount of exp needed for a guild to reach a whole level
     * (integer). For example, if a guild had 0 experience, this method would return how much
     * experience they would need before they reached level 5.0. If the level parameter is less than
     * 0, an {@link IllegalArgumentException} may be thrown.
     *
     * @param level The level reached with the returned amount of exp; should be an integer
     * @return The total amount of experience needed to reach that level
     */
    static double getTotalExpToFullLevel(double level) {
        double expNeeded = 0;

        for (int i = 0; i < (int) level; i++) {
            expNeeded += getExpFromLevelToNext(i);
        }

        return expNeeded;
    }

    /**
     * This method returns a guild's current progress to the next level as a floating-point number
     * between 0 (inclusively) and 1 (exclusively). For example, if a guild has an experience value
     * exactly halfway between the exp needed for their current level (floored) and the exp needed
     * for the next level (floored), this method will return 0.5. If the experience parameter is
     * less than 0, an {@link IllegalArgumentException} will be thrown.
     *
     * @param exp The total experience gathered by a guild; should be >= 0
     * @return The guild's progress to the next level as a percentage between 0 and 1
     */
    static double getPercentageToNextLevel(double exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Experience value must be >= 0");
        }

        double currentLvl = getLevel(exp);
        // Exp needed for the current whole level (excluding progress)
        double totalExpForCurrentLvl = getTotalExpToFullLevel(currentLvl);
        // Exp diff between current whole level and next whole level
        double expToNextLvl = getExpFromLevelToNext(currentLvl);

        return (exp - (totalExpForCurrentLvl)) / expToNextLvl;
    }
}
