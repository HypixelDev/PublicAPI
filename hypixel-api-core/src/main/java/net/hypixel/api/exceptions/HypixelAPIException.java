package net.hypixel.api.exceptions;

public abstract class HypixelAPIException extends RuntimeException {

    protected HypixelAPIException(String message) {
        super(message);
    }

}
