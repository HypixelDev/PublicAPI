package net.hypixel.api.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MINUTES;

public class RateLimiter {
	private final Object lock = new Object();

	private final ScheduledExecutorService resetter = Executors.newSingleThreadScheduledExecutor();
	private volatile int limitPerMinute;
	private int actionsThisMinute;

	/**
	 * @param limitPerMinute Maximum amount of times {@link RateLimiter#beforeAction()} can be called in the same minute
	 *                       before it begins blocking threads until the next minute.
	 */
	public RateLimiter(int limitPerMinute) {
		this.limitPerMinute = limitPerMinute;
		resetter.scheduleAtFixedRate(this::reset, 1, 1, MINUTES);
	}

	private void reset() {
		synchronized ( lock ) {
			actionsThisMinute = 0;
			lock.notifyAll();
		}
	}

	/**
	 * Set the action queue limit to be used by {@link RateLimiter#beforeAction()}.
	 *
	 * @param limitPerMinute The new limit.
	 */
	public void setRate(int limitPerMinute) {
		this.limitPerMinute = limitPerMinute;
	}

	/**
	 * Blocks the current thread in the event that the action queue has been filled for this minute.
	 * Unblocks when the queue refreshes.
	 *
	 * @throws InterruptedException If interrupted while waiting.
	 */
	public void beforeAction() throws InterruptedException {
		synchronized ( lock ) {
			while (actionsThisMinute >= limitPerMinute) {
				lock.wait();
			}

			actionsThisMinute++;
		}
	}

	/**
	 * Shut down the internal executor service.
	 */
	public void shutdown() {
		resetter.shutdown();
	}
}
