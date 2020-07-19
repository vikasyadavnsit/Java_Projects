package com.battleramp.concurrency.utility;

public class ThreadLocalTask2 {

	public static void run() {
		UserThreadLocalContext.local.set(UserThreadLocalContext.local.get() + 1);
		System.out
				.println(Thread.currentThread().getName() + " : Initial Value - " + UserThreadLocalContext.local.get());
		ThreadLocalTask3.run();
	}

}
