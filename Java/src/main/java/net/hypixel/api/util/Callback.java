package net.hypixel.api.util;

import net.hypixel.api.reply.AbstractReply;

public abstract class Callback<T extends AbstractReply> {
    private final Class<T> clazz;

    public Callback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public abstract void callback(Throwable failCause, T result);

    public final Class<T> getClazz() {
        return clazz;
    }
}
