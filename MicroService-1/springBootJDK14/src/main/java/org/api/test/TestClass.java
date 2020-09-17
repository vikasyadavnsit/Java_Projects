package org.api.test;

class A {

	A() {
		this(20);
		System.out.println("Inside A Default Constructoor");
	}

	A(int a) {
		System.out.println("Inside A Paramaterized Constructoor");
	}

}

class B extends A {
	B() {
		System.out.println("Inside B Default Constructoor");
	}

	B(int a) {
		this();
		System.out.println("Inside B Paramaterized Constructoor");
	}

	public static void main(String[] args) {
		new B(2);
	}

}