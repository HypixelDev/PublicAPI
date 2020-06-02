package net.hypixel.api.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface IGuildLeveling extends ILeveling {

    List<Integer> EXP_NEEDED = Collections.unmodifiableList(Arrays.asList(
        100000,
        150000,
        250000,
        500000,
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

    int MAX_EXP_NEEDED = EXP_NEEDED.get(EXP_NEEDED.size() - 1);

    static double getLevel(double exp) {
        for (int level = 0; ; level++) {
            int needed = level >= EXP_NEEDED.size() ? MAX_EXP_NEEDED : EXP_NEEDED.get(level);
            exp -= needed;

            if (exp < 0) {
                return level;
            }
        }
    }

    static double getExactLevel(double exp) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static double getExpFromLevelToNext(double level) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static double getTotalExpToLevel(double level) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static double getTotalExpToFullLevel(double level) {
        double expNeeded = 0;

        for (int i = 0; i < level; i++) {
            expNeeded += i >= EXP_NEEDED.size() ? MAX_EXP_NEEDED : EXP_NEEDED.get(i);
        }

        return expNeeded;
    }

    static double getPercentageToNextLevel(double exp) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
