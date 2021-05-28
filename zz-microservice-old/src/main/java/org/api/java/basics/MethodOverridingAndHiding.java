package org.api.java.basics;

class Parent {
	public static void show() {
		System.out.println("Parent Static Show Method");
	}

	public void show2() {
		System.out.println("Parent Non Static show2 Method");
	}

	public final void show3() {
		System.out.println("Parent show3 Final Method");
	}

	private void show4() {
		System.out.println("Parent show4 Private Method");
	}

}

class Child extends Parent {
	public static void show() {
		System.out.println("Child Static Show Method");
	}

	public void show2() {
		System.out.println("Child Non Static show2 Method");
	}
}

public class MethodOverridingAndHiding {

	public static void main(String[] args) {
		Parent p = new Child();
		// Static Method cannot be inherited and cannot be overidden ( Method Hiding)
		p.show();
		p.show2();

		// Final Method cannot be overidden but can be inherited
		Child c = new Child();
		c.show3();
		// Private Method cannot be overidden and cannot be inherited
	}

}
