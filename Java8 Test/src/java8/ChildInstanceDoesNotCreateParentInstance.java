package java8;

class Fruit {
	public Fruit() {
		System.out.println("Fruit has HashCode: " + this.hashCode() + " : className : " + this.getClass().getName());
	}
}

class Apple extends Fruit {
	public Apple() {
		System.out.println("Apple has HashCode: " + this.hashCode() + " : className : " + this.getClass().getName());
	}
}

public class ChildInstanceDoesNotCreateParentInstance {
	public static void main(String[] args) {
		Apple obj = new Apple();
		// ********* when creating object of child class, a constructor of parent class
		// is
		// called first but its instance is not created , only child class instance is
		// created
	}
}