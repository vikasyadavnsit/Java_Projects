package java8.concurrency;

public class PrintOddEvenUsingJoin {

	static int count = 1;

	public static void main(String[] args) throws InterruptedException {

		while (count <= 100) {
			Thread t1 = new Thread(() -> {
				System.out.println(count++);
			});
			t1.start();
			t1.join();

			Thread t2 = new Thread(() -> {
				System.out.println(count++);
			});
			t2.start();
			t2.join();
		}
	}

}
