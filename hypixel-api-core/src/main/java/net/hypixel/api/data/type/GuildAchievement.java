package net.hypixel.api.data.type;

/**
 * Types of achievements that can be earned by guilds on Hypixel.
 * <p>
 * Created using <a href="https://github.com/slothpixel/hypixelconstants">slothpixel/hypixelconstants</a>
 * as a reference.
 */
public enum GuildAchievement {
    /**
     * A tiered achievement based on the highest amount of experience earned by a guild in a single
     * day.
     */
    EXPERIENCE_KINGS,

    /**
     * A tiered achievement based on the highest number of members a guild has had online at the
     * same time.
     */
    ONLINE_PLAYERS,

    /**
     * A tiered achievement based on a guild's highest level.
     */
    PRESTIGE,

    /**
     * A tiered achievement based on the highest number of combined wins (in mini-games) between
     * members of a guild on the same day.
     */
    WINNERS
}
