package org.api.utility.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLockTask implements Runnable {

	private ReentrantLock lock1;
	private ReentrantLock lock2;
	int whichThread;

	DeadLockTask(ReentrantLock lock1, ReentrantLock lock2, int whichThread) {
		this.lock1 = lock1;
		this.lock2 = lock2;
		this.whichThread = whichThread;
	}

	@Override
	public void run() {
		try {
			if (this.whichThread == 1) {
				lock1.lock();
				System.out.println(Thread.currentThread().getName() + " Entering into Dead Lock Sitution.");
				Thread.sleep(1000);
				lock2.lock();
			} else {
				lock2.lock();
				System.out.println(Thread.currentThread().getName() + " Entering into Dead Lock Sitution.");
				Thread.sleep(1000);
				lock1.lock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock1.unlock();
		lock2.unlock();
		System.out.println(Thread.currentThread().getName() + " released form Dead lock");
	}

}
