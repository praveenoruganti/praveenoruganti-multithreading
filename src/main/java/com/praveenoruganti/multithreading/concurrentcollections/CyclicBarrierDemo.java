package com.praveenoruganti.multithreading.concurrentcollections;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {

	private CyclicBarrier barrier;
	private int threadId;
	public Processor(CyclicBarrier barrier, int threadId) {
		super();
		this.barrier = barrier;
		this.threadId = threadId;
	}
	public void run() {
		System.out.println("Thread"+threadId +" Started.");
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException ignored) {
		}
	}
}

public class CyclicBarrierDemo {

	public static void main(String[] args){
		CyclicBarrier barrier = new CyclicBarrier(3);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 3; i++) {
			executor.submit(new Processor(barrier,i));
		}
		executor.shutdown();
	}
}
