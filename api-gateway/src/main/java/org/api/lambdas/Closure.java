package org.api.lambdas;

import java.util.function.Predicate;

public class Closure {

	public static void main(String[] args) {

		int a = 10;
		int b = 20;

		System.out.println(new Predicate<Integer>() {
			@Override
			public boolean test(Integer t) {
				return t > b;
			}
		}.test(a));

		//local variable b is accessible inside lambda as the scope of the lambda is , where it is defined
		Predicate<Integer> p = x -> x > b;
		System.out.println(p.test(a));
	}
}
