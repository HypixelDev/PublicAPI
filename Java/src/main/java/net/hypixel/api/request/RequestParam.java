package net.hypixel.api.request;

import net.hypixel.api.util.APIUtil;

import java.util.UUID;
import java.util.function.Function;

@SuppressWarnings("unused")
public enum RequestParam {

    KEY(RequestType.KEY, "key", String.class),

    PLAYER_BY_NAME(RequestType.PLAYER, "name", String.class),
    PLAYER_BY_UUID(RequestType.PLAYER, "uuid", UUID.class, APIUtil.UUID_STRIPPER),

    GUILD_BY_NAME(RequestType.FIND_GUILD, "byName", String.class),
    GUILD_BY_PLAYER_UUID(RequestType.FIND_GUILD, "byUuid", UUID.class, APIUtil.UUID_STRIPPER),
    GUILD_BY_ID(RequestType.GUILD, "id", String.class),

    FRIENDS_BY_UUID(RequestType.FRIENDS, "uuid", UUID.class, APIUtil.UUID_STRIPPER),

    SESSION_BY_UUID(RequestType.SESSION, "uuid", UUID.class, APIUtil.UUID_STRIPPER);

    private static final RequestParam[] v = values();

    private final RequestType requestType;
    private final String queryField;
    private final Class valueClass;
    private final Function<Object, String> valueSerializer;

    RequestParam(RequestType requestType, String queryField, Class<?> valueClass) {
        this(requestType, queryField, valueClass, null);
    }

    RequestParam(RequestType requestType, String queryField, Class<?> valueClass, Function<Object, String> valueSerializer) {
        this.requestType = requestType;
        this.queryField = queryField;
        this.valueClass = valueClass;
        this.valueSerializer = valueSerializer;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getQueryField() {
        return queryField;
    }

    public Class getValueClass() {
        return valueClass;
    }

    public String serialize(Object value) {
        return valueSerializer == null ? (String) value : valueSerializer.apply(value);
    }
}
