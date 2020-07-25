package com.praveenoruganti.multithreading.concurrentcollections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {

	private BlockingQueue<Integer> blockingQueue;

	public Producer(BlockingQueue<Integer> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		int counter = 0;
		while (true) {
			try {
				blockingQueue.put(counter);
				System.out.println("putting items in queue " + counter);
				counter++;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}
}

class Consumer implements Runnable {

	private BlockingQueue<Integer> blockingQueue;

	public Consumer(BlockingQueue<Integer> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				int number = blockingQueue.take();
				System.out.println("taking items from the queue " + number);
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}
}

public class BlockingQueueDemo {

	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		new Thread(producer).start();
		new Thread(consumer).start();

	}

}
