package com.battleramp.concurrency.utility;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.battleramp.concurrency.services.TestService;

@Component
public class ConcurrentUtil {
	volatile int parallelThreads = 3;

	@Autowired
	private TestService testService;

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
		return "Sequencial Odd Even Count Completed";
	}

	public String readWriteLock() {
		return "";
	}

}
