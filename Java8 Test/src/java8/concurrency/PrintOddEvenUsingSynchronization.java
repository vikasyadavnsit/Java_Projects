package java8.concurrency;

class PC {
	void oddCount() throws InterruptedException {
		int count = -1;
		while (count + 2 <= 100) {
			synchronized (this) {
				System.out.println("ODD Count  : " + (count += 2));
				notify();
				wait();
			}
		}
	}

	void evenCount() throws InterruptedException {
		int count = -2;
		while (count + 2 <= 100) {
			synchronized (this) {
				System.out.println("EVEN Count : " + (count += 2));
				notify();
				wait();
			}
		}
	}
}

public class PrintOddEvenUsingSynchronization {

	public static void main(String[] args) {

		PC obj = new PC();

		new Thread(() -> {
			try {
				obj.evenCount();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		;

		new Thread(() -> {
			try {
				obj.oddCount();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		;

	}

}
