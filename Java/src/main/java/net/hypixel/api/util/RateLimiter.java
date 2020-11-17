package net.hypixel.api.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MINUTES;

public class RateLimiter
{
	private int limitPerMinute = 120;
	private int requestsThisMinute;
	private final Object lock = new Object();
	private final ScheduledExecutorService refresher = Executors.newSingleThreadScheduledExecutor();

	public RateLimiter()
	{
		refresher.scheduleAtFixedRate(() -> {
			requestsThisMinute = 0;
			lock.notifyAll();
		}, 1, 1, MINUTES);
	}

	public void setRate(int limitPerMinute)
	{
		this.limitPerMinute = limitPerMinute;
	}

	public void block() throws InterruptedException
	{
		synchronized ( lock )
		{
			while (requestsThisMinute >= limitPerMinute)
			{
				lock.wait();
			}

			requestsThisMinute++;
		}
	}

	public void shutdown()
	{
		refresher.shutdown();
	}
}
