package test;

import java.util.HashSet;
import java.util.Set;

class temp {
	int a;
	String str;

	temp(int a, String str) {
		this.a = a;
		this.str = str;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

}

class Test {

	public static void main(String[] args) {

		Set<temp> set1 = new HashSet<>();

		System.out.println(set1.add(new temp(1, "vikas")));
		System.out.println(set1.add(new temp(2, "vikas")));
		System.out.println(set1.add(new temp(1, "vikas")));

		System.out.println(set1.size());

	}
}