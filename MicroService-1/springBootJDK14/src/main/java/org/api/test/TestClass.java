package org.api.test;

public class TestClass extends Thread {
	public static void main(String[] args) {
		System.out.println("Inside Main: Before executing thread on this class");
		new TestClass().start();
		new TestClass().run();
		// Both Method will execute normally, without performing any operation
	}
}