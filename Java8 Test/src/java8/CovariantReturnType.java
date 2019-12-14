package java8;

class A {
}

class B extends A {
}

class Base {
	A fun() {
		System.out.println("Base Fun");
		return new A();
	}

	void m1() {

	}

	void m2() {

	}
}

class Derived extends Base {
	B fun() {
		super.fun();
		System.out.println("Derived Fun");
		return new B();
	}

	@Override
	void m1() throws ArithmeticException {

	}

// Cannot Throw Checked Exception in Child Class	
	/*
	 * @Override void m2() throws Exception{
	 * 
	 * }
	 */

}

public class CovariantReturnType {

	public static void main(String[] args) {
		Base b = new Base();
		b.fun();

		b = new Derived();
		b.fun();

	}

}
