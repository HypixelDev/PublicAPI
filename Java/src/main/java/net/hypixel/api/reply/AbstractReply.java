package net.hypixel.api.reply;

public abstract class AbstractReply {

  protected boolean throttle;
  protected boolean success;
  protected String cause;

  public final boolean isThrottle() {
    return throttle;
  }

  public final boolean isSuccess() {
    return success;
  }

  public final String getCause() {
    return cause;
  }

  public final void ensureValidity() {
    if (isThrottle()) {
      throw new APIThrottleException();
    }
    if (!isSuccess()) {
      throw new HypixelApiException(getCause());
    }
  }

  @Override
  public String toString() {
    return "AbstractReply{"
        + "throttle="
        + throttle
        + ", success="
        + success
        + ", cause='"
        + cause
        + '\''
        + '}';
  }
}
