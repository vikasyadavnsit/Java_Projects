package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

class AllPossiblePaths {

	static int arr[][] = 
			   {{ 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 }, 
                { 1, 0, 1, 0, 1, 1, 1, 1, 1, 1 }, 
                { 1, 1, 1, 0, 1, 0, 1, 0, 0, 1 }, 
                { 0, 0, 0, 0, 1, 0, 1, 0, 0, 1 }, 
                { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 }, 
                { 1, 0, 1, 1, 1, 1, 0, 1, 0, 1 }, 
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
                { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, 
                { 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 }}; 
	static int N = arr.length;
	static int M = arr[0].length;
	static int visited[][] = new int[N][M];
	static int path = 0;
	static ArrayList<int[][]> list = new ArrayList<>();

	public static void main(String[] args) {
		graph(0, 0, N - 1, M - 1);
		path = Integer.MAX_VALUE;
		list.parallelStream().forEach(t -> {
			int count=0;
			for(int [] temp : t)
				for(int temp2 : temp)
					if(temp2 ==1)
						++count;
			if(count < path) {
				path = count;
				visited = t;
			}
		});
		System.out.println(path);
		printPaths(visited);
	}

	static void printPaths(int temp[][]) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(temp[i][j] + ", ");
			}
			System.out.println();
		}
		System.out.println("#############################");
	}

	static boolean isValid(int i, int j) {
		if (i < 0 || j < 0 || i >= N | j >= M)
			return false;
		return true;
	}

	static void graph(int i, int j, int x, int y) {

		if (i == x && j == y) {
			visited[i][j] = 1;
			++path;
			if (list.isEmpty()) {
				list.add(Arrays.stream(visited).map(int[]::clone).toArray(int[][]::new));
			} else {
				AtomicBoolean result = new AtomicBoolean(false);
				list.stream().forEach(t -> {
					if (!Arrays.deepEquals(t, visited)) {
						result.set(true);
					}
				});
				if (result.get()) {
					list.add(Arrays.stream(visited).map(int[]::clone).toArray(int[][]::new));
				}
			}
		}

		if (arr[i][j] == 1 && visited[i][j] == 0) {
			visited[i][j] = 1;
			if (isValid(i, j + 1) && arr[i][j + 1] == 1) {
				graph(i, j + 1, x, y);
			}
			if (isValid(i + 1, j) && arr[i + 1][j] == 1) {
				graph(i + 1, j, x, y);
			}
			if (isValid(i - 1, j) && arr[i - 1][j] == 1) {
				graph(i - 1, j, x, y);
			}
			if (isValid(i, j - 1) && arr[i][j - 1] == 1) {
				graph(i, j - 1, x, y);
			}
			visited[i][j] = 0;
		}

	}
}
