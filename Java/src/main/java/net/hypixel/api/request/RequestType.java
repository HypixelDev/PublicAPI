/* © 2017 Hypixel Inc. All Rights Reserved. */
package net.hypixel.api.request;

import net.hypixel.api.reply.*;

@SuppressWarnings("unused")
public enum RequestType {

    PLAYER("player", PlayerReply.class),
    FIND_GUILD("findGuild", FindGuildReply.class),
    GUILD("guild", GuildReply.class),
    FRIENDS("friends", FriendsReply.class),
    SESSION("session", SessionReply.class),
    KEY("key", KeyReply.class),
    BOOSTERS("boosters", BoostersReply.class);

    private final String key;
    private final Class<? extends AbstractReply> replyClass;

    RequestType(String key, Class<? extends AbstractReply> replyClass) {
        this.key = key;
        this.replyClass = replyClass;
    }

    public String getKey() {
        return key;
    }

    public Class<? extends AbstractReply> getReplyClass() {
        return replyClass;
    }
}
