package net.hypixel.api.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MINUTES;

public class RateLimiter {
	private final Object lock = new Object();
	private final ScheduledExecutorService resetter = Executors.newSingleThreadScheduledExecutor();
	private volatile int limitPerMinute = 120;
	private int requestsThisMinute;

	public RateLimiter() {
		resetter.scheduleAtFixedRate(this::reset, 1, 1, MINUTES);
	}

	private void reset() {
		synchronized ( lock ) {
			requestsThisMinute = 0;
			lock.notifyAll();
		}
	}

	public void setRate(int limitPerMinute) {
		this.limitPerMinute = limitPerMinute;
	}

	public void block() throws InterruptedException {
		synchronized ( lock ) {
			while (requestsThisMinute >= limitPerMinute) {
				lock.wait();
			}

			requestsThisMinute++;
		}
	}

	public void shutdown() {
		resetter.shutdown();
	}
}
