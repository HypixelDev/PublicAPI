package net.hypixel.api.pets;

import java.util.Map;

public class Pet {

    private static final int[] EXPERIENCE_LEVELS = {
            200, 210, 230, 250, 280, 310, 350, 390, 450, 500, 570, 640, 710, 800, 880, 980, 1080, 1190, 1300, 1420,
            1540, 1670, 1810, 1950, 2100, 2260, 2420, 2580, 2760, 2940, 3120, 3310, 3510, 3710, 3920, 4140, 4360, 4590,
            4820, 5060, 5310, 5560, 5820, 6090, 6360, 6630, 6920, 7210, 7500, 7800, 8110, 8420, 8740, 9070, 9400, 9740,
            10080, 10430, 10780, 11150, 11510, 11890, 12270, 12650, 13050, 13440, 13850, 14260, 14680, 15100, 15530,
            15960, 16400, 16850, 17300, 17760, 18230, 18700, 19180, 19660, 20150, 20640, 21150, 21650, 22170, 22690,
            23210, 23750, 24280, 24830, 25380, 25930, 26500, 27070, 27640, 28220, 28810, 29400, 30000
    };

    private Map<String, Object> stats;
    private int level;
    private int experience;
    private String name;

    public Pet(Map<String, Object> stats) {
        this.stats = stats;

        if (stats != null) {
            if (stats.get("experience") != null) {
                experience = ((Number) stats.get("experience")).intValue();
            }
            if (stats.get("name") != null) {
                name = (String) stats.get("name");
            }
        }

        updateLevel();
    }

    public String getName() {
        return name;
    }

    public double getAverageHappiness() {
        double attributeAverage = 0;
        for (PetAttribute attribute : PetAttribute.values()) {
            attributeAverage += getAttribute(attribute);
        }

        return attributeAverage / PetAttribute.values().length;
    }

    public int getAttribute(PetAttribute attribute) {
        @SuppressWarnings("unchecked")
        Map<String, Object> attributeObject = (Map<String, Object>) stats.get(attribute.name());

        if (attributeObject == null) {
            return 1;
        }

        Object timestampObj = attributeObject.get("timestamp");
        Object valueObj = attributeObject.get("value");
        if (!(timestampObj instanceof Number) || !(valueObj instanceof Number)) {
            return 1;
        }

        long currentTime = System.currentTimeMillis();
        long timestamp = ((Number) timestampObj).longValue();
        int value = ((Number) valueObj).intValue();

        long timeElapsed = currentTime - timestamp;
        long minutesPassed = timeElapsed / (1000 * 60);
        long iterations = (long) Math.floor(minutesPassed / 5);

        return Math.max(0, Math.round(value - iterations * attribute.getDecay()));
    }

    public boolean updateLevel() {
        int xp = experience;
        int level = 1;
        for (int xpLevel : EXPERIENCE_LEVELS) {
            if (xp < xpLevel) {
                break;
            } else {
                xp -= xpLevel;
                level++;
            }
        }
        this.level = level;
        return false;
    }

    public int getLevel() {
        return level;
    }

    public int getExperienceUntilLevel(int level) {
        int xp = 0;
        for (int i = 0; i < Math.min(level - 1, 100); i++) {
            xp += EXPERIENCE_LEVELS[i];
        }
        return xp;
    }

    public int getLevelProgress() {
        return experience - getExperienceUntilLevel(level);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "stats=" + stats +
                ", level=" + level +
                ", experience=" + experience +
                ", name='" + name + '\'' +
                '}';
    }
}
