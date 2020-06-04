package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Test {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = 1;// Integer.parseInt(br.readLine());
		while (t-- > 0) {
			String[] sArr = "3 3 3".split("\\s+");
			int m = sArr.length;

			int arr[][] = new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };

			StringBuffer sb = new StringBuffer();
			int i = 0, j = 0, k = 0, n = 0, o = 0;
			m = Integer.parseInt(sArr[0]);
			n = Integer.parseInt(sArr[1]);
			o = Integer.parseInt(sArr[2]);

			while (i < m && j < n && k < o) {
				if ((arr[0][i] == arr[1][j]) && (arr[0][i] == arr[2][k])) {
					sb.append(arr[0][i] + " ");
					i++;
					j++;
					k++;
					while (i < m && arr[0][i] == arr[0][i - 1])
						i++;
					while (j < n && arr[1][j] == arr[1][j - 1])
						j++;
					while (k < o && arr[2][k] == arr[2][k - 1])
						k++;
				} else if (arr[0][i] < arr[1][j]) {
					i++;
				} else if (arr[1][j] < arr[2][k]) {
					j++;
				} else {
					k++;
				}
			}
			if (sb.length() == 0)
				System.out.println(-1);
			else {
				System.out.println(sb.toString());
			}
		}
	}

}