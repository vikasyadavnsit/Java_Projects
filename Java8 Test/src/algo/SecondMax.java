package algo;

public class SecondMax {
	public static void main(String[] args) {

		String[] strArr = { "4", "4", "-1", "3", "2" };

		long max = Long.MIN_VALUE;
		long secondMax = Long.MIN_VALUE;
		Long e;
		for (int i = 0; i < strArr.length; i++) {
			e = Long.parseLong(strArr[i]);
			if (e > max) {
				if (max > secondMax) {
					secondMax = max;
				}
				max = e;
			}
			if (e > secondMax && e != max && secondMax < max) {
				secondMax = e;
			}
		}
		if (secondMax == Long.MIN_VALUE)
			secondMax = -1;
		System.out.println(secondMax);
	}
}
