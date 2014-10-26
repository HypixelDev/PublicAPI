package net.hypixel.api.util;

public abstract class Callback<T> {
    private final Class<T> clazz;

    public Callback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public abstract void callback(Throwable failCause, T result);

    public final Class<T> getClazz() {
        return clazz;
    }
}
