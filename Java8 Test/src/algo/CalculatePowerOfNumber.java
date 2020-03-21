package algo;

public class CalculatePowerOfNumber {

	public static long calculatePower(int num, int pow, long arr[]) {

		if (num == 0)
			return 0;

		if (arr[pow] != 0) {
			return arr[pow];
		}

		if (pow < 2) {
			if (pow < 1)
				return arr[pow] = 1;
			else
				return arr[pow] = num;
		}

		if (pow % 2 == 0) {
			return arr[pow] = num * calculatePower(num, pow / 2, arr);
		} else {
			return arr[pow] = (calculatePower(num, pow / 2, arr) * calculatePower(num, (pow / 2) + 1, arr));
		}
	}

	public static void main(String arg[]) {
		int num = 3, pow = 5;
		System.out.println(calculatePower(num, pow, new long[pow + 1]));
	}
}
