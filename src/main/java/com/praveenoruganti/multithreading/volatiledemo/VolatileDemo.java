package com.praveenoruganti.multithreading.volatiledemo;

import java.util.Scanner;

// Volatile Keyword,  the volatile modifier guarantees that any thread that
// reads a field will see the most recently written value
class WorkerThread extends Thread {
	private volatile boolean isRunning = true;

	@Override
	public void run() {
		while (isRunning) {
			System.out.println("Running");

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopWorker() {
		isRunning = false;
	}
}

public class VolatileDemo {

	public static void main(String[] args) {
		WorkerThread workerThread = new WorkerThread();
		workerThread.start();
		// Wait for the enter key
		System.out.println("Enter something to stop the thread,\nVolatile variable running will be forced to true :");
		try (Scanner scanner = new Scanner(System.in);) {
			scanner.nextLine();
		}
		workerThread.stopWorker();

	}
}