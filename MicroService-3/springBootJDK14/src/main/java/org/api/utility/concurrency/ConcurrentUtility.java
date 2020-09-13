package org.api.utility.concurrency;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.Phaser;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.api.dao.concurrency.ReadWriteLockData;
import org.api.services.concurrency.ConcurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.util.internal.ThreadLocalRandom;

@Component
public class ConcurrentUtility {
	volatile int parallelThreads = 3;

	@Autowired
	private ConcurrencyService testService;

	public String sequencialSum() throws Exception {
		LocalDateTime start = LocalDateTime.now();
		List<Integer> list = testService.getLargeIntList().subList(0, 10_000);
		BigInteger sum = new BigInteger("0");
		for (int i : list) {
			Thread.sleep(1);
			sum = sum.add(BigInteger.valueOf(i * 991));
		}
		LocalDateTime end = LocalDateTime.now();
		return "Total Computation Time In Millis : " + Duration.between(start, end).toMillis() + "<br>Result : "
				+ sum.toString();
	}

	public String parallelSum() throws Exception {
		LocalDateTime start = LocalDateTime.now();

		List<Integer> list = testService.getLargeIntList().subList(0, 10_000);
		BigInteger totalSum = new BigInteger("0");
		int partitionSize = 1_000;
		Collection<Callable<BigInteger>> callables = new ArrayList<>();
		ExecutorService service = Executors.newCachedThreadPool();

		for (int i = 0; i < list.size(); i += partitionSize) {
			callables.add(new ParalleSum(list.subList(i, Math.min(i + partitionSize, list.size()))));
		}

		// It waits for all threads to complete, then result the result
		List<Future<BigInteger>> taskFutureList = service.invokeAll(callables);

		for (Future<BigInteger> temp : taskFutureList)
			totalSum = totalSum.add(temp.get());

		LocalDateTime end = LocalDateTime.now();
		service.shutdown();
		return "Total Computation Time In Millis : " + Duration.between(start, end).toMillis() + "<br>Result : "
				+ totalSum.toString();
	}

	public String printSequencialCharThroughMultipleThreads(int threadCount) throws InterruptedException {
		this.parallelThreads = threadCount;
		AtomicInteger ai = new AtomicInteger(1);
		CountDownLatch latch = new CountDownLatch(this.parallelThreads);
		for (int i = parallelThreads; i > 0; i--) {
			new Thread(new SequencialCharThroughMultipleThreads(ai, latch, parallelThreads, i)).start();
		}
		latch.await();
		return "Printed Output of " + this.parallelThreads + " Threads";
	}

	public String threadLocalImplementation() throws Exception {
		for (int i = 0; i < 10; i++) {
			new UserThreadLocalContext().start();
		}
		return "Executed Successfully : Different Threads have different context of thier thread local variables.";
	}

	public String oddEvenCountUsingReentrantLock(int maxCount) {
		OddEvenReentrantLock.count = 1;
		OddEvenReentrantLock.maxCount = maxCount;
		new Thread(() -> OddEvenReentrantLock.oddCount()).start();
		new Thread(() -> OddEvenReentrantLock.evenCount()).start();
		return "Sequencial Odd Even Count Completed Upto " + maxCount;
	}

	public String readWriteLockImplementation() {
		List<String> list = ReadWriteLockData.get();
		Random rand = new Random(987654321L);
		for (int i = 0; i < 10; i++) {
			// Create Reader Thread
			if (rand.nextInt(10) % 3 != 0) {
				new Thread(() -> new ReadWriteLock(list).readList()).start();
			}
			// Create Writer Thread
			else {
				new Thread(() -> new ReadWriteLock(list).writeList()).start();
			}
		}
		return "ReadWriteLock Implementation Successfull.";
	}

	public String interruptsImplementation() throws Exception {
		List<Integer> list = testService.getLargeIntList().subList(0, 100);
		int partitionSize = 10;

		List<Future<List<Integer>>> taskFutureList = new ArrayList<>();
		ExecutorService service = Executors.newCachedThreadPool();

		for (int i = 0; i < list.size(); i += partitionSize) {
			Future<List<Integer>> future = service
					.submit(new ThreadInterrupts(list.subList(i, Math.min(i + partitionSize, list.size()))));
			taskFutureList.add(future);
		}

		Thread.sleep(500);
		List<Integer> primes = new ArrayList<>();
		for (Future<List<Integer>> futurePlaceholder : taskFutureList) {
			if (futurePlaceholder.isDone()) {
				primes.addAll(futurePlaceholder.get());
			} else {
				System.out.println("Interrupted a thread.");
				futurePlaceholder.cancel(true);
			}
		}

		primes.stream().sorted().forEach(System.out::println);
		service.shutdown();
		return "Thread Interrupt Work";
	}

	public String phaserImplementation() {
		// Phaser can act as both Cyclic Barrier and CoundDownLatch, along with dynamic
		// addition and removal of parties to it.
		ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(20);
		// Self Registration
		Phaser phaser = new Phaser();
		phaser.register();
		for (int i = 0; i < 5; i++) {
			scheduledService.schedule(new PhaserTasks(phaser), ThreadLocalRandom.current().nextInt(500),
					TimeUnit.MILLISECONDS);
		}

		System.out.println("Waiting for " + phaser.getRegisteredParties() + " parties of Phase " + phaser.getPhase()
				+ " to Finish");
		phaser.arriveAndAwaitAdvance();
		phaser.arriveAndDeregister();

		System.out.println("###### Starting New Dynamic Phases #######");

		scheduledService.shutdown();
		System.out.println("######## Initiated Shut Down for Scheduled Service ########");
		while (!scheduledService.isTerminated()) {
		}
		System.out.println("Shut Down Completes");
		return "Phaser Implemented Successfully.";
	}

	public String forkJoinPoolImplementation() throws Exception {
		// RecursiveAction Doesn't return result
		// RecursiveTask Return the result
		List<Integer> list = testService.getLargeIntList();

		ForkJoinPool forkJoinPool = new ForkJoinPool();
		List<Integer> divisibleBy11 = forkJoinPool.invoke(new ForkJoinRecursiveTask(list.size(), list));
		StringJoiner sj = new StringJoiner("<br>");
		for (Integer temp : divisibleBy11)
			sj.add(temp.toString());
		forkJoinPool.shutdown();
		return "Fork Join Implementation Successful :<br>" + sj.toString();
	}

	public String completableFutureImplementation() throws Exception {
		List<Integer> list = testService.getLargeIntList().subList(100, 200);

		// ArrayList<CompletableFuture<List<Integer>>> collection = new ArrayList<>();

		CompletableFuture<List<Integer>> completableFuture = CompletableFuture
				.supplyAsync(() -> CompletableFutureTasks.processTask1(list))
				.thenApplyAsync(x -> CompletableFutureTasks.processTask2(x)).exceptionallyAsync(e -> {
					System.out.println("Exception Occured");
					return null;
				});

		CompletableFuture<List<Integer>> completableFuture2 = CompletableFuture
				.supplyAsync(() -> CompletableFutureTasks.processTask1(list))
				.thenApplyAsync(x -> CompletableFutureTasks.processTask2(x)).exceptionallyAsync(e -> {
					System.out.println("Exception Occured");
					return null;
				});

		List<Integer> combined = Stream.of(completableFuture, completableFuture2).map(CompletableFuture::join)
				.flatMap(x -> x.stream()).collect(Collectors.toList());

		if (completableFuture.isCompletedExceptionally()) {
			return "Completed Sucessfully, But exception occured.";
		}
		StringJoiner sj = new StringJoiner("<br>");
		for (Integer temp : combined)
			sj.add(temp.toString());
		return "Completable Future Executed Successfully <br>" + sj.toString();
	}

	public String longAdderLongAccumulatorImplementation() {
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
		return "LongAdder LongAccumulator Implementation Executed Successfully";
	}

	public String deadLockDetectionImplementation() {
		ScheduledExecutorService deadLockDetectionService = Executors.newScheduledThreadPool(2);
		Thread t = new Thread(new DeadLockDetectionTask());
		t.setDaemon(true);
		deadLockDetectionService.scheduleAtFixedRate(t, 2, 20, TimeUnit.SECONDS);

		ForkJoinPool threadPool = new ForkJoinPool(5);
		ReentrantLock lock1 = new ReentrantLock();
		ReentrantLock lock2 = new ReentrantLock();
		IntStream.range(0, 2).forEach(x -> threadPool.execute(new DeadLockTask(lock1, lock2, x)));
		threadPool.shutdown();
		return "DeadLock Detection Implementation Executed Successfully";
	}

	public String spinLockImplementation() {
		ExecutorService service = Executors.newFixedThreadPool(4);
		SpinLock spinLock = new SpinLock();
		IntStream.range(0, 4).forEach(x -> service.submit(new SpinLockTask(spinLock)));
		return "SpinLock Implementation Executed Successfully";
	}

	public String semaphoreImplementation(int semaphoreSize) {
		ExecutorService service = Executors.newFixedThreadPool(4);
		Semaphore semaphore = new Semaphore(semaphoreSize);

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
		return "Semaphore Implementation Executed Successfully";
	}

	public String reuseThreadImplementation() {
		return "Method Not implemented Yet";
	}
}
