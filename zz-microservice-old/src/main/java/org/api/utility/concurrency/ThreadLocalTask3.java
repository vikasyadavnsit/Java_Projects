package org.api.utility.concurrency;

public class ThreadLocalTask3 {

	public static void run() {
		UserThreadLocalContext.local.set(UserThreadLocalContext.local.get() + 1);
		System.out
				.println(Thread.currentThread().getName() + " : Initial Value - " + UserThreadLocalContext.local.get());
	}

}
