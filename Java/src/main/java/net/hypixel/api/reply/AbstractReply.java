package net.hypixel.api.reply;

public abstract class AbstractReply {

    protected boolean throttle;
    protected boolean success;
    protected String cause;

    transient protected int limitLeft;
    transient protected int limitReset;

    public boolean isThrottle() {
        return throttle;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getCause() {
        return cause;
    }

    public int getRequestAmountLeft()
    {
        return limitLeft;
    }

    public void setRequestAmountLeft(int limitLeft)
    {
        this.limitLeft = limitLeft;
    }

    public int getSecondsTillReset()
    {
        return limitReset;
    }

    public void setSecondsTillReset(int limitReset)
    {
        this.limitReset = limitReset;
    }


    @Override
    public String toString() {
        return "AbstractReply{" +
                "throttle=" + throttle +
                ", success=" + success +
                ", cause='" + cause + '\'' +
                ", limitLeft=" + limitLeft +
                ", limitReset=" + limitReset +
                '}';
    }
}
