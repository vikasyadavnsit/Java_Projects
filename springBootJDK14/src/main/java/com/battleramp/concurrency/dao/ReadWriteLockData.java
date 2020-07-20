package com.battleramp.concurrency.dao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReadWriteLockData {

	// Singleton Pattern using Lazy Initalization for Multithreaded Environment
	private static class Holder {
		static final ReadWriteLockData INSTANCE = new ReadWriteLockData();
	}

	private static List<String> list;
	private static volatile ReadWriteLockData obj;

	private ReadWriteLockData() {
		list = IntStream.range(1, 10).mapToObj(x -> ("List Item " + String.valueOf(x))).collect(Collectors.toList());
	}

	public static ReadWriteLockData getInstance() {
		return Holder.INSTANCE;
	}

	public static List<String> get() {
		getInstance();
// Double Check Locking with volatile on obj
// 		if (obj == null) {
//			synchronized (ReadWriteLockData.class) {
//				if (obj == null) {
//					obj = new ReadWriteLockData();
//				}
//			}
//		}
		return list;
	}
}
