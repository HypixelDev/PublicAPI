/* © 2017 Hypixel Inc. All Rights Reserved. */
package net.hypixel.api.util;

import net.hypixel.api.reply.AbstractReply;

@FunctionalInterface
public interface Callback<T extends AbstractReply> {

    public void callback(Throwable failCause, T result);
}
