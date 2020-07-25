package com.praveenoruganti.multithreading.threadcreation;

public class SimpleThread extends Thread {

	public void run() {
		System.out.println("Starting " + currentThread().getName());
	}

	public static void main(String[] args) {
		Thread t1 = new SimpleThread();
		t1.setName("t1");
		Thread t2 = new SimpleThread();
		t2.setName("t2");
		t1.start();
		t2.start();

	}

}

