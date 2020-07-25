package com.praveenoruganti.multithreading.interrupt;

import java.util.Random;

public class ThreadInterruptDemo {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting...");
		Runnable runnable1 = () -> {
			Random random = new Random();
			for (int i = 0; i < 1E8; i++) {
				System.out.println(Thread.currentThread().isInterrupted());
				if (Thread.currentThread().isInterrupted()) {
					System.out.println(Thread.currentThread().isInterrupted());
					System.out.println("Interrupted");
					break;
				}
				Math.sin(random.nextDouble());
			}
		};

		Thread t1 = new Thread(runnable1);
		t1.start();
		Thread.sleep(500);
		t1.interrupt();
		t1.join();
		System.out.println("Finished...");

	}
}
