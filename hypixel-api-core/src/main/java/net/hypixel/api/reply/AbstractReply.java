package net.hypixel.api.reply;

public abstract class AbstractReply {

    protected boolean success;
    protected String cause;

    public boolean isSuccess() {
        return success;
    }

    public String getCause() {
        return cause;
    }

    @Override
    public String toString() {
        return "AbstractReply{" +
                "success=" + success +
                ", cause='" + cause + '\'' +
                '}';
    }
}
