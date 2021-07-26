package net.hypixel.api.exceptions;

public class BadResponseException extends HypixelAPIException {

    public BadResponseException(String responseCause) {
        super(responseCause);
    }

}
