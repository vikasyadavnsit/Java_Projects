package com.battleramp.concurrency.utility;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenReentrantLock {

	// True to make it fair
	static ReentrantLock lock = new ReentrantLock();
	static Condition currentThreadCondition = lock.newCondition();
	static int count = 1;
	static int maxCount = 10;

	public static void oddCount() {
		lock.lock();
		while (count < maxCount) {
			while (count % 2 == 0)
				try {
					currentThreadCondition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			System.out.println(Thread.currentThread().getName() + " : " + count);
			++count;
			currentThreadCondition.signalAll();
		}
		lock.unlock();
	}

	public static void evenCount() {
		lock.lock();
		while (count < maxCount) {
			while (count % 2 != 0)
				try {
					currentThreadCondition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			System.out.println(Thread.currentThread().getName() + " : " + count);
			++count;
			currentThreadCondition.signalAll();
		}
		lock.unlock();
	}

}
