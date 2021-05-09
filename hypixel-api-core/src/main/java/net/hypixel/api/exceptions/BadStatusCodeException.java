package net.hypixel.api.exceptions;

public class BadStatusCodeException extends HypixelAPIException {
    private final int statusCode;
    private final String responseCause;

    public BadStatusCodeException(int statusCode, String responseCause) {
        super("Got a bad status code " + statusCode + ", cause \"" + responseCause + "\"");
        this.statusCode = statusCode;
        this.responseCause = responseCause;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseCause() {
        return responseCause;
    }
}
