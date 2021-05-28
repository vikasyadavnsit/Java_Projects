package org.api.lambdas;

public class RunnableExample {

	public static void main(String[] args) {

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Printed Inside Runnable");
			}
		});

		t.run();

		Thread t2 = new Thread(() -> System.out.println("Printed Inside Lambda Runnable"));
		t2.run();
	}

}
