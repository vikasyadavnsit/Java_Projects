package org.api.test;

public class TestClass {

	public static void main(String[] args) {
		String a = "qwertrwqwerwqwerwerwqwe";
		int carr[] = new int[26];

		for (int i = 0; i < a.length(); i++) {
			++carr[a.charAt(i) - 97];
		}

		int o = 0;
		for (int i = 0; i < 26; i++) {
		}

		System.out.println(o > 1 ? false : true);

	}
}
