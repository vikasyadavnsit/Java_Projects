package itgi.portal.concurrency.utility;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SemaphoreTask implements Runnable {

	Semaphore semaphore;

	public SemaphoreTask(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + " waiting to acquire lock");
			semaphore.tryAcquire(2000, TimeUnit.MILLISECONDS);
			Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		semaphore.release();
		System.out.println(Thread.currentThread().getName() + " Executed Successfully ########");
	}

}
