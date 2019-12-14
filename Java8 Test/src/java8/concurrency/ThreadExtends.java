package java8.concurrency;

public class ThreadExtends extends Thread {
	static int count = 0;

	public void run() {
		System.out.println(Thread.currentThread().getName() + " is alive" + Thread.currentThread().isAlive()
				+ " Count Index : " + ++count);
	}

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			ThreadExtends t = new ThreadExtends();
			t.start();
		}
	}

}
