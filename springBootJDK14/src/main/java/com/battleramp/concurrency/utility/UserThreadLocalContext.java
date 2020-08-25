package com.battleramp.concurrency.utility;

public class UserThreadLocalContext extends Thread {

	static ThreadLocal<Integer> local = new ThreadLocal<>();

	@Override
	public void run() {
		local.set(1);
		System.out.println(Thread.currentThread().getName() + " : Initial Value - " + local.get());
		ThreadLocalTask1.run();
	}
}
