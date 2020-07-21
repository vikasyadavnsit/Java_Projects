package com.battleramp.concurrency.utility;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class CompletableFutureTasks {

	public static List<Integer> processTask1(List<Integer> list) {
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
			return list.stream().map(x -> x * 2).collect(Collectors.toList());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Integer> processTask2(List<Integer> list) {
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(500));
			return list.stream().map(x -> x * 3).collect(Collectors.toList());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Integer> processTask3(List<Integer> list) {
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(500));
			return list.stream().map(x -> x * 4).collect(Collectors.toList());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
