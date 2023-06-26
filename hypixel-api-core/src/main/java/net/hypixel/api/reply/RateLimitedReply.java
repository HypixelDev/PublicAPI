package net.hypixel.api.reply;

import net.hypixel.api.http.RateLimit;

public abstract class RateLimitedReply extends AbstractReply {
    private RateLimit rateLimit;

    public RateLimit getRateLimit() {
        return rateLimit;
    }

    public void setRateLimit(RateLimit rateLimit) {
        this.rateLimit = rateLimit;
    }

    @Override
    public String toString() {
        return "RateLimitedReply{" +
                "rateLimit=" + rateLimit +
                "} " + super.toString();
    }
}
