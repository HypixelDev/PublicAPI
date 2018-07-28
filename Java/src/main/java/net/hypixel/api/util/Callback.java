package net.hypixel.api.util;

import net.hypixel.api.reply.AbstractReply;

@FunctionalInterface
public interface Callback<T extends AbstractReply> {

    void callback(Throwable failCause, T result);
}
