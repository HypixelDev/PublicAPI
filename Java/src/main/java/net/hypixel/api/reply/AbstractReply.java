package net.hypixel.api.reply;

public abstract class AbstractReply {

    protected boolean throttle;
    protected boolean success;
    protected String cause;

    transient protected int requestAmountRemaining;
    transient protected int timeUntilLimitReset;

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

    /**
     * Should only be used in {@link net.hypixel.api.HypixelAPI#get(Class, String, Object...)}
     * This has no use after this value has been set once
     * @param requestAmountRemaining
     */
    public void setRequestAmountRemaining(int requestAmountRemaining)
    {
        this.requestAmountRemaining = requestAmountRemaining;
    }

    public int getSecondsUntilReset()
    {
        return timeUntilLimitReset;
    }

    /**
     * Should only be used in {@link net.hypixel.api.HypixelAPI#get(Class, String, Object...)}
     * This has no use after this value has been set once
     * @param timeUntilReset
     */
    public void setSecondsUntilReset(int timeUntilReset)
    {
        this.timeUntilLimitReset = timeUntilReset;
    }


    @Override
    public String toString() {
        return "AbstractReply{" +
                "throttle=" + throttle +
                ", success=" + success +
                ", cause='" + cause + '\'' +
                ", limitLeft=" + requestAmountRemaining +
                ", limitReset=" + timeUntilLimitReset +
                '}';
    }
}
