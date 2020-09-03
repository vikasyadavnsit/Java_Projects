package itgi.portal.concurrency.utility;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

public class SpinLockTask implements Runnable {
	SpinLock lock = new SpinLock();

	SpinLockTask(SpinLock lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		Instant start = Instant.now();
		System.out.println(Thread.currentThread().getName() + " trying to acquire lock");
		this.lock.lock();
		System.out.println(Thread.currentThread().getName() + " Acquired lock after "
				+ Duration.between(start, Instant.now()).toMillis() + " Milliseconds");
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " Completed Sucessfully");
		this.lock.unlock();
	}

}
