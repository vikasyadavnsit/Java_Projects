package org.api.utility.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class SequencialCharThroughMultipleThreads implements Runnable {

	volatile int parallelThreads;
	AtomicInteger ai;
	CountDownLatch latch;
	int threadState;
	int iterations;

	SequencialCharThroughMultipleThreads(AtomicInteger ai, CountDownLatch latch, int parallelThreads, int threadState) {
		this.ai = ai;
		this.latch = latch;
		this.parallelThreads = parallelThreads;
		this.threadState = threadState;
		iterations = 0;
	}

	@Override
	public void run() {
		// System.out.println(Thread.currentThread().getName() + " waiting...");
		synchronized (ai) {
			// System.out.println(Thread.currentThread().getName() + " Inside Sync
			// Block...");
			while (iterations < this.parallelThreads) {
				while (ai.intValue() != this.threadState) {
					try {
						ai.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println(Thread.currentThread().getName() + " : " + (char) (this.threadState - 1 + 65));
				++iterations;
				if (ai.intValue() == this.parallelThreads) {
					ai.set(1);
				} else {
					ai.getAndIncrement();
				}
				if (iterations == this.parallelThreads) {
					latch.countDown();
				}
				ai.notifyAll();
			}
		}
	}

}
