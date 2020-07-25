package com.praveenoruganti.multithreading.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
	private static int counter=0;
	private static Lock lock= new ReentrantLock();

	public static void increment() {
		lock.lock();
		for(int i=0;i<10000;i++) {
			counter++;
		}
		lock.unlock();
	}

	public static void main(String[] args) throws Exception {

		Runnable runnable1= () -> {
			increment();
		};
		Thread t1= new Thread(runnable1);
		Runnable runnable2= () -> {
			increment();
		};
		Thread t2= new Thread(runnable2);

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		}catch(InterruptedException ie) {
			System.out.println(ie.getMessage());
		}

		System.out.println("Counter is: "+ counter);

	}
}
