package net.hypixel.api.http;

public class HypixelHttpResponse {

    private final int statusCode;
    private final String body;
    private final RateLimit rateLimit;

    @Deprecated
    public HypixelHttpResponse(int statusCode, String body) {
        this(statusCode, body, null);
    }

    public HypixelHttpResponse(int statusCode, String body, RateLimit rateLimit) {
        this.statusCode = statusCode;
        this.body = body;
        this.rateLimit = rateLimit;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    public RateLimit getRateLimit() {
        return rateLimit;
    }
}
