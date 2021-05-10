package net.hypixel.api.http;

public class HypixelHttpResponse {

    private final int statusCode;
    private final String body;

    public HypixelHttpResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }
}
