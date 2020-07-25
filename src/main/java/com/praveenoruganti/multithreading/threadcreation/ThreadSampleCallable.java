package com.praveenoruganti.multithreading.threadcreation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadSampleCallable implements Callable<String> {

	private int command;

	public String call() throws Exception {
		return "Starting " + command + " " + Thread.currentThread().getName();
	}

	public ThreadSampleCallable(int command) {
		this.command = command;
	}

	public static void main(String args[]) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		List<Future<String>> list = new ArrayList<Future<String>>();
		for (int i = 0; i < 10; i++) {
			Future<String> future = executor.submit(new ThreadSampleCallable(i));
			list.add(future);
		}
		// now retrieving the result
		for (Future<String> future : list) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException ie) {
				System.out.println(ie);
			} catch (ExecutionException ee) {
				System.out.println(ee);
			}
		}
		// This will make the executor accept no new threads and finish all existing
		// threads in queue executor.shutdown();
		// wait until all threads are finish
		executor.awaitTermination(10, TimeUnit.SECONDS);
		System.out.println("Finished all threads");
		// Shutdown terminates the main thread
		executor.shutdown();
	}
}
