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

    public int getLimit() {
        return limit;
    }

    public int getRemaining() {
        return remaining;
    }

    public int getReset() {
        return reset;
    }

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
