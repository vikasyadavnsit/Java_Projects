package org.api.test;

import java.util.Deque;
import java.util.LinkedList;

public class TestClass {

	public static void main(String[] args) {

		int n = 10, k = 5;
		int[] arr = new int[] { 1, 8, 3, 4, 5, 2, 9, 1, 5, 0 };
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

//		for (int i = 0; i < n; i++) {
//			for (int j = i; j < i + k && j < n; j++) {
//				min = Math.min(min, arr[j]);
//				max = Math.max(max, min);
//			}
//		}

		Deque<Integer> q = new LinkedList<>();
		q.add(arr[0]);

		for (int i = 1; i < k; i++) {
			while (!q.isEmpty() && q.peekLast() > arr[i]) {
				q.removeLast();
			}
			if (q.isEmpty() || q.peekLast() < arr[i])
				q.add(arr[i]);
		}
		max = Math.max(max, q.peekFirst());

		for (int i = k; i < n; i++) {
			while (!q.isEmpty() && q.peekLast() > arr[i]) {
				q.removeLast();
			}
			if (q.isEmpty() || q.peekLast() < arr[i])
				q.add(arr[i]);
			max = Math.max(max, q.peekFirst());
		}

		System.out.println(max);
	}
}