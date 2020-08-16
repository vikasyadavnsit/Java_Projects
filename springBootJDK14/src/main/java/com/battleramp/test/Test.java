package com.battleramp.test;

import java.util.HashMap;

public class Test {

	static int p[] = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
			89, 97, 101 };

	static int sherlockAndAnagrams(String s) {
		int a = 0, M = (int)Math.pow(10, 9) + 7;
		HashMap<Integer, Integer> m = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			int c = 1;
			for (int j = i; j < s.length(); j++) {
				c = (c * p[s.charAt(j) - 97]) % M;
				if (!m.containsKey(c)) {
					m.put(c, 1);
				} else {
					m.put(c, m.get(c) + 1);
				}

			}
		}
		for(int t : m.keySet()) {
			
		}
		return a;
	}

	public static void main(String[] args) {
		sherlockAndAnagrams("sdf");
	}
}
