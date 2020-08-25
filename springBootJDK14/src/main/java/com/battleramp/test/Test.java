package com.battleramp.test;

import java.util.Arrays;

class Test {

	public static void main(String[] args) throws Exception {
		int[] a = new int[] { 1, 1, 1, 2, 5 };
		int x = 6, len = a.length;

		int l = 0, h = len, mid = -1;

		while (l < h) {
			mid = l + (h - l) / 2;
			if (x >= a[mid])
				l = mid + 1;
			else
				h = mid;
		}

		System.out.println(l);
		System.out.println(Arrays.binarySearch(a, x));
	}

}