package com.praveenoruganti.multithreading.locks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
	ReadWriteLock lock = new ReentrantReadWriteLock();

	private int count = 0;

	public int incrementAndGetCount() {
		lock.writeLock().lock();
		try {
			count = count + 1;
			return count;
		} finally {
			lock.writeLock().unlock();
		}
	}

	public int getCount() {
		lock.readLock().lock();
		try {
			return count;
		} finally {
			lock.readLock().unlock();
		}
	}
}
