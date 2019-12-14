package java8;

class OuterClass {

	private int a = 10;
	protected int b = 20;

	public void show() {
		System.out.println("OuterClass NonStatic Show Method");
	}

	private void show2() {
		System.out.println("OuterClass Private Show2 Method");
	}

	public class InnerClass extends OuterClass {

		public void show() {
			System.out.println("InnerClass NonStatic Show Method");
		}

		public void show2() {
			System.out.println("InnerClass Private Show2 Method");
		}

		public void test() {
			System.out.println("Integer a: " + a);
		}
	}

	public static void main(String[] args) {
		OuterClass o = new OuterClass();
		InnerClass i = o.new InnerClass();
		o.show2();
		o = i;
		o.show2();
		o.show();
		i.test();
	}

}
