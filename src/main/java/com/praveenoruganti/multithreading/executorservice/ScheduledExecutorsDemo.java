package com.praveenoruganti.multithreading.executorservice;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorsDemo {
	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		Runnable task = () -> {
			System.out.println("Executing Task At " + System.nanoTime());
		};

		System.out.println("Submitting task at " + System.nanoTime() + " to be executed after 5 seconds.");
		scheduledExecutorService.schedule(task, 5, TimeUnit.SECONDS);

		scheduledExecutorService.shutdown();
		ScheduledExecutorService scheduledExecutorService1 = Executors.newScheduledThreadPool(1);
		System.out.println("scheduling task to be executed every 2 seconds with an initial delay of 0 seconds");
		scheduledExecutorService1.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

	}
}
