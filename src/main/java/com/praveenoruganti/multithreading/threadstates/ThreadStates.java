package com.praveenoruganti.multithreading.threadstates;

class SampleThread implements Runnable {

	public void run() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException ie) {
			System.out.println(ie.getMessage());
		}
		System.out.println("State of thread1 while it called join() on thread2 -- " + ThreadStates.thread1.getState());

		try {
			Thread.sleep(200);
		} catch (InterruptedException ie) {
			System.out.println(ie.getMessage());
		}
	}

}

public class ThreadStates implements Runnable {

	public static Thread thread1;
	public static ThreadStates threadStates;

	public static void main(String[] args) {
		threadStates = new ThreadStates();
		thread1 = new Thread(threadStates);
		// thread1 created and it is in NEW state
		System.out.println("State of thread1 after creating it -- " + thread1.getState());
		thread1.start();
		// thread1 moved to RUNNABLE state
		System.out.println("State of thread1 after calling start() -- " + thread1.getState());
	}

	public void run() {
		SampleThread sampleThread = new SampleThread();
		Thread thread2 = new Thread(sampleThread);
		// thread2 created and it is in NEW state
		System.out.println("State of thread2 after creating it -- " + thread2.getState());
		thread2.start();
		// thread2 moved to RUNNABLE state
		System.out.println("State of thread2 after calling start() -- " + thread2.getState());

		// moving thread1 to WAITING state
		try {
			// moving thread1 to TIMED_WAITING state
			Thread.sleep(200);
		} catch (InterruptedException ie) {

		}

		// state of thread2 after calling sleep method
		System.out.println("State of thread2 after calling sleep() -- " + thread2.getState());

		try {
			// waiting for thread2 to die
			thread2.join();
		} catch (InterruptedException ie) {

		}

		System.out.println("State of thread2 when its finished executing -- " + thread2.getState());

	}

}
