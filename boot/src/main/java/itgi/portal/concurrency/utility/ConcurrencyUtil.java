package itgi.portal.concurrency.utility;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ConcurrencyUtil {

	public static void main(String[] args) {
		// Uncomment the task to run

		/* ##### Semaphore Implementation ######## */
		// semaphoreImplementation();

		/* ##### Spin Lock Implementation ######## */
		// spinLockImplementation();

		/* ##### Dead Lock Detection Implementation ######## */
		// deadLockDetectionImplementation();

		/* ##### Custom Blocking Queue Implementation ######## */
		// deadLockDetectionImplementation();

		/* ##### LongAdder and LongAccumulator Implementation ######## */
		// longAdderLongAccumulatorImplementation();

		/* ##### LongAdder and LongAccumulator Implementation ######## */
		// longAdderLongAccumulatorImplementation();

		/* ##### Reuse Thread Implementation ######## */
		// reuseThreadImplementation();

		String s = "kkkk";
		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j < s.length(); j++) {
				for (int k = i; k <= j; k++) {
					System.out.print(s.charAt(k));
				}
				System.out.println();
			}
		}
	}

	private static void reuseThreadImplementation() {

	}

	private static void longAdderLongAccumulatorImplementation() {
		ExecutorService service = Executors.newFixedThreadPool(10);

		// Long Adder Implementation
		LongAdder adder = new LongAdder();
		IntStream.rangeClosed(1, 20).forEach(x -> service.execute(new AtomicLongAdder(adder)));
		System.out.println("Waiting For Long Added Completion");
		try {
			Thread.sleep(100);
			System.out.println("Long Adder Sum : " + adder.sum());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// LongAccumulater Implementation
		LongAccumulator accumulator = new LongAccumulator((x, y) -> x * y, 1);
		IntStream.rangeClosed(1, 20).forEach(x -> service.execute(new AtomicLongAccumulator(accumulator)));
		System.out.println("Waiting For Accumulator Completion");
		try {
			Thread.sleep(100);
			System.out.println("Long Accumulator Total Product is : " + accumulator.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static void deadLockDetectionImplementation() {
		ScheduledExecutorService deadLockDetectionService = Executors.newScheduledThreadPool(2);
		Thread t = new Thread(new DeadLockDetectionTask());
		t.setDaemon(true);
		deadLockDetectionService.scheduleAtFixedRate(t, 2, 20, TimeUnit.SECONDS);

		ForkJoinPool threadPool = new ForkJoinPool(5);
		ReentrantLock lock1 = new ReentrantLock();
		ReentrantLock lock2 = new ReentrantLock();
		IntStream.range(0, 2).forEach(x -> threadPool.execute(new DeadLockTask(lock1, lock2, x)));
		threadPool.shutdown();
	}

	public static void spinLockImplementation() {
		ExecutorService service = Executors.newFixedThreadPool(4);
		SpinLock spinLock = new SpinLock();
		IntStream.range(0, 4).forEach(x -> service.submit(new SpinLockTask(spinLock)));
	}

	public static void semaphoreImplementation() {
		ExecutorService service = Executors.newFixedThreadPool(4);
		Semaphore semaphore = new Semaphore(3);

		IntStream.range(0, 10).forEach(x -> service.submit(new SemaphoreTask(semaphore)));

		try {
			service.awaitTermination(1500, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		service.shutdown();
		System.out.println("Shutting Down Executor");
		while (!service.isTerminated())
			;
		System.out.println("Executor Sucessfully Shutdown");
	}
}
