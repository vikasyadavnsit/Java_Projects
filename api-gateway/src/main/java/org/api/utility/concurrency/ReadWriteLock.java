package org.api.utility.concurrency;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {

	public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	public static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
	public static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
	public List<String> list;

	public ReadWriteLock(List<String> list) {
		this.list = list;
	}

	public void readList() {
		readLock.lock();
		System.out.println("Reader Thread " + Thread.currentThread().getName());
		for (String item : list) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// System.out.println(Thread.currentThread().getName() + " : " + item);
		}
		readLock.unlock();
	}

	public void writeList() {
		writeLock.lock();
		System.out.println("Writer Thread " + Thread.currentThread().getName());
		list.add("New List Item " + list.size());
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		list.add("New List Item " + list.size());
		writeLock.unlock();
	}

}
