package net.hypixel.api.reply;

public abstract class AbstractReply {

    protected boolean throttle;
    protected boolean success;
    protected String cause;

    public boolean isThrottle() {
        return throttle;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getCause() {
        return cause;
    }

    @Override
    public String toString() {
        return "AbstractReply{" +
                "throttle=" + throttle +
                ", success=" + success +
                ", cause='" + cause + '\'' +
                '}';
    }
}
