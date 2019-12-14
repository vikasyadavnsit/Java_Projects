package airtel;

import java.util.ArrayList;

public class StateMachine {

	public static void findPath(ArrayList<Integer> path, int ss, int es, int[] startState, int endState[][]) {
		if (ss > es) {
			return;
		}

		boolean sExist = false;
		int ssIndex = -1;
		for (int i = 0; i < startState.length; i++) {
			if (ss == startState[i]) {
				ssIndex = i;
				sExist = true;
			}
		}
		if (!sExist) {
			return;
		}

		for (int i = 0; i < startState.length; i++) {

			for (int k = 0; k < endState[i].length; k++) {
				if (es == endState[i][k] && i >= ssIndex) {
					path.add(startState[i]);
				}
			}
		}

	}

	public static void main(String[] args) {

		int startState[] = new int[] { 1, 2, 3, 4, 5, 7, 8 };
		int endState[][] = new int[][] { { 2, 3 }, { 4, 5, 6 }, { 7, 8 }, { 9 }, { 9 }, { 10 }, { 10 } };

		int ss = 1;
		int es = 9;

		ArrayList<Integer> path = new ArrayList<Integer>();
		findPath(path, ss, es, startState, endState);

		if (path.size() > 0) {
			System.out.println("Yes");
			for (Integer index : path) {
				if (ss != index)
					System.out.println(ss + ">" + index + ">" + es);
				else
					System.out.println(index + ">" + es);
			}
		} else {
			System.out.println("No");
		}
	}

}
