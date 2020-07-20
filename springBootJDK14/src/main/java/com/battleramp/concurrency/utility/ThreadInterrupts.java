package com.battleramp.concurrency.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import io.netty.util.internal.ThreadLocalRandom;

public class ThreadInterrupts implements Callable<List<Integer>> {
	List<Integer> primesList;
	List<Integer> subList;
	static Predicate<Integer> isPrime = x -> IntStream.range(2, (int) Math.sqrt(x)).noneMatch(r -> x % r == 0);

	public ThreadInterrupts(List<Integer> list) {
		this.subList = list;
		this.primesList = new ArrayList<>();
	}

	@Override
	public List<Integer> call() {
		try {
			for (int a : this.subList) {
				Thread.sleep(ThreadLocalRandom.current().nextInt(100));
				if (isPrime.test(a)) {
					this.primesList.add(a);
				}
				if (Thread.currentThread().isInterrupted()) {
					throw new InterruptedException();
				}
			}
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " has been Interrupted");
			return null;
		}
		System.out.println(Thread.currentThread().getName() + " calculated the primes.");
		return this.primesList;
	}
}
