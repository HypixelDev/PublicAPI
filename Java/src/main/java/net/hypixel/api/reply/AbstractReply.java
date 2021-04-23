package net.hypixel.api.reply;

import com.google.gson.annotations.SerializedName;

public abstract class AbstractReply {

    protected boolean throttle;
    protected boolean success;
    protected String cause;

    public static final String REQUEST_REMAINING_FIELD_NAME = "requests_remaining";
    public static final String TIME_UNTIL_RESET_FIELD_NAME = "time_until_reset";

    @SerializedName(REQUEST_REMAINING_FIELD_NAME)
    protected final int requestAmountRemaining = -1; // -1 indicates this field has not been deserialized yet
    @SerializedName(TIME_UNTIL_RESET_FIELD_NAME)
    protected final int timeUntilLimitReset = -1; // -1 indicates this field has not been deserialized yet

    public boolean isThrottle() {
        return throttle;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getCause() {
        return cause;
    }

    public int getRequestAmountRemaining()
    {
        return requestAmountRemaining;
    }

    public int getSecondsUntilReset()
    {
        return timeUntilLimitReset;
    }


    @Override
    public String toString() {
        return "AbstractReply{" +
                "throttle=" + throttle +
                ", success=" + success +
                ", cause='" + cause + '\'' +
                ", limit_left=" + requestAmountRemaining +
                ", limit_reset=" + timeUntilLimitReset +
                '}';
    }
}
