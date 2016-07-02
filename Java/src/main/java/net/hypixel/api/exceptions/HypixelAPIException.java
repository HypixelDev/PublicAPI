package net.hypixel.api.exceptions;

@SuppressWarnings("unused")
public class HypixelAPIException extends RuntimeException {

    public HypixelAPIException() {
    }

    public HypixelAPIException(String message) {
        super(message);
    }

    public HypixelAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    public HypixelAPIException(Throwable cause) {
        super(cause);
    }

    public HypixelAPIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
