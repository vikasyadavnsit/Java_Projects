package recursion;

public class TrickyRecursion1 {
	public static Object test(int a, int b) {
		if (b == 0)
			return null;
		return test(a, b--);
	}

	public static void main(String arg[]) {
		test(5, 2);
	}
}
