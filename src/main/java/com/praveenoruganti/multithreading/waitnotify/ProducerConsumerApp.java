package com.praveenoruganti.multithreading.waitnotify;

public class ProducerConsumerApp {

	public static void main(String[] args) throws InterruptedException {
		final Processor processor = new Processor();
		Runnable runnable1 = () -> {
			try {
				processor.produce();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		};
		Thread t1 = new Thread(runnable1);

		Runnable runnable2 = () -> {
			try {
				processor.consume();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		};
		Thread t2 = new Thread(runnable2);

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}

}
