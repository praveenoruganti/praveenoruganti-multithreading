package com.praveenoruganti.multithreading.concurrentcollections;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(10);
		for (int i = 1; i <= 100; i = i + 10) {
			Printer printer = new Printer(i, i + 9, latch, i * 10);
			printer.start();
		}
// The main task waits for 10 threads
		try {
			latch.await();
			// Main thread has started
			System.out.println(Thread.currentThread().getName() + " has finished");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Printer extends Thread {
	private int start;
	private int end;
	private CountDownLatch latch;
	private int delay;

	public Printer(int start, int end, CountDownLatch latch, int delay) {
		super();
		this.start = start;
		this.end = end;
		this.latch = latch;
		this.delay = delay;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(delay);
			for (int i = start; i <= end; i++) {
				System.out.println(Thread.currentThread().getName() + " : " + i + "\t" + "*");
			}
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		System.out.println("*****************");
		latch.countDown();
	}
}
