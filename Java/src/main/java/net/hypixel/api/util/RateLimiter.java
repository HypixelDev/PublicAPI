package net.hypixel.api.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MINUTES;

public class RateLimiter
{
	private int limitPerMinute = 120;
	private int requestsThisMinute;
	private final ScheduledExecutorService resetter = Executors.newSingleThreadScheduledExecutor();

	public RateLimiter() {
		resetter.scheduleAtFixedRate(this::reset, 1, 1, MINUTES);
	}

	private synchronized void reset() {
		requestsThisMinute = 0;
		notifyAll();
	}

	public void setRate(int limitPerMinute) {
		this.limitPerMinute = limitPerMinute;
	}

	public synchronized void block() throws InterruptedException {
		while (requestsThisMinute >= limitPerMinute) {
			wait();
		}

		requestsThisMinute++;
	}

	public void shutdown() {
		resetter.shutdown();
	}
}
