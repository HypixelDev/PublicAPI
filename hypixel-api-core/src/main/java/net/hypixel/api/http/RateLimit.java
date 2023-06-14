package net.hypixel.api.http;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RateLimit {
    private final int limit;
    private final int remaining;
    private final int reset;
    private final Date resetAt;

    public RateLimit(int limit, int remaining, int reset) {
        this.limit = limit;
        this.remaining = remaining;
        this.reset = reset;
        this.resetAt = new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(reset));
    }

    /**
     * @return the total limit allowed for the used API key per interval
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @return the remaining amount of requests for the used API key during this interval
     */
    public int getRemaining() {
        return remaining;
    }

    /**
     * @return the time in seconds until the limit interval resets
     */
    public int getReset() {
        return reset;
    }

    /**
     * @return the date at which time the limit interval resets, this date won't be accurate to the millisecond due to
     * the only context being in seconds
     */
    public Date getResetAt() {
        return resetAt;
    }

    @Override
    public String toString() {
        return "RateLimit{" +
                "limit=" + limit +
                ", remaining=" + remaining +
                ", reset=" + reset +
                ", resetAt=" + resetAt +
                '}';
    }
}
