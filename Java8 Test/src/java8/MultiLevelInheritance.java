package java8;

interface aa {
	public default void show() {
		System.out.println("IntefaceA show method");
		aa.showWithParameters(90000);
	}

	public static void showWithParameters(int a) {
		System.out.println("Parameter A : " + a);
	}
}

interface bb {
	public default void show() {
		System.out.println("IntefaceB show method");
	}
}

public class MultiLevelInheritance implements aa, bb {

	@Override
	public void show() {
		aa.super.show();
		bb.super.show();
	}

	public static void main(String[] args) {
		MultiLevelInheritance t = new MultiLevelInheritance();
		t.show();
	}
}
