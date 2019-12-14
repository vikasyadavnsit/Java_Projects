package java8.concurrency;

import java.util.Random;

public class ThreadImplements implements Runnable {

	static int count = 0;

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new ThreadImplements());
			t.start();
		}
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " is alive" + Thread.currentThread().isAlive()
				+ " Count Index : " + ++count + "  State of the thread " + Thread.currentThread().getState());
		try {
			Thread.sleep((long) Math.abs(Math.random() * 100 + 1));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
