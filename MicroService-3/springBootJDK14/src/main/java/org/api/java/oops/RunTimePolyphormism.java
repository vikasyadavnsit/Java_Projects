package org.api.java.oops;

public class RunTimePolyphormism {

	static class A {
		void print() {
			System.out.println("Printing from class : " + this.getClass().getName());
		}
	}

	static class B extends A {
		void print() {
			System.out.println("Printing from class :" + this.getClass().getName());
		}
	}

	static class C extends B {
		void print() {
			System.out.println("Printing from class:" + this.getClass().getName());
		}

		void cprint() {
			System.out.println("Custom Printing from class :" + this.getClass().getCanonicalName());
		}
	}

	public static void main(String[] args) {
		// [ This code will work only when inner classes are static]
		A obj1 = new RunTimePolyphormism.B(); // or new B();
		obj1.print();

		// [ Remove static from the inner classes, and then try]
		// A obj = new RunTimePolyphormism().new B();
		// obj.print(); // [This will call the method on class B]
		// obj.cprint(); [The method is undefined in the class A, so it will
		// produce an error]

		// static class A {
		// void print() {
		// System.out.println("Printing from class : " +
		// this.getClass().getName());
		// }
		// }
		//
		// static class B extends A {
		// void print() {
		// System.out.println("Printing from class :" +
		// this.getClass().getName());
		// }
		// }
		//
		// static class C extends B {
		// void cprint() {
		// System.out.println("Custom Printing from class :" +
		// this.getClass().getCanonicalName());
		// }
		// }

		// [--It will print class c]
		A obj3 = new C();
		obj3.print();
	}

}
