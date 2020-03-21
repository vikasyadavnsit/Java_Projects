package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class M {

	static int visited[][];

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int te = 1;// Integer.parseInt(bf.readLine());

		while (te-- > 0) {

			int n = 5;// Integer.parseInt(bf.readLine());
			String str[] = "31 100 65 12 18 10 13 47 157 6 100 113 174 11 33 88 124 41 20 140 99 32 111 41 20".split("\\s+");
			int[][] arr = new int[n][n];
			visited = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(str[(n * i) + j]);
				}
			}
			printPaths(arr, n);

			System.out.println(graph(0, 0, n - 1, arr));
			printPaths(visited, n);

		}

	}

	static void printPaths(int temp[][], int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(temp[i][j] + ", ");
			}
			System.out.println();
		}
	}

	static boolean isValid(int i, int j, int n) {
		if (i < 0 || j < 0 || i > n | j > n)
			return false;
		return true;
	}

	static int graph(int i, int j, int n, int arr[][]) {

		if (i == n && j == n) {
			visited[i][j] = 0;
			return arr[i][j];
		}

		int result[] = new int[4];
		Arrays.fill(result, Integer.MAX_VALUE);
		if (visited[i][j] == 0) {
			visited[i][j] = 1;
			printPaths(visited, n+1);
			System.out.println("#############");
			//System.out.println(arr[i][j]);
			if (isValid(i, j + 1, n)) {
				result[0] = graph(i, j + 1, n, arr);
			}
			if (isValid(i + 1, j, n)) {
				result[1] = graph(i + 1, j, n, arr);
			}
			if (isValid(i - 1, j, n)) {
				result[2] = graph(i - 1, j, n, arr);
			}
			if (isValid(i, j - 1, n)) {
				result[3] = graph(i, j - 1, n, arr);
			}
			visited[i][j] = 0;
		}else
			return Integer.MAX_VALUE;
		System.out.println(arr[i][j] + Arrays.stream(result).min().getAsInt());
		return arr[i][j] + Arrays.stream(result).min().getAsInt();
	}
}
