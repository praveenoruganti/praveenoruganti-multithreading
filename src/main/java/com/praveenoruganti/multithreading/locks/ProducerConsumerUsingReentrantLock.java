package com.praveenoruganti.multithreading.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void producer() throws InterruptedException {
		lock.lock();
		System.out.println("Producer method...");
		condition.await();
		System.out.println("Producer again...");
		lock.unlock();
	}

	public void consumer() throws InterruptedException {
		lock.lock();
		Thread.sleep(2000);
		System.out.println("Consumer method...");
		condition.signal();
		lock.unlock();
	}
}

public class ProducerConsumerUsingReentrantLock {

	public static void main(String[] args) {
		final Worker worker=new  Worker();
		Runnable runnable1 = () -> {
			try {
				worker.producer();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		};
		Thread t1 = new Thread(runnable1);

		Runnable runnable2 = () -> {
			try {
				worker.consumer();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		};
		Thread t2 = new Thread(runnable2);

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

	}

}
