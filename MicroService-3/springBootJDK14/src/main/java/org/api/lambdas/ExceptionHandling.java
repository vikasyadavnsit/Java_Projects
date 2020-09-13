package org.api.lambdas;

import java.util.function.BiConsumer;

public class ExceptionHandling {
	public static void main(String[] args) {

		int[] nums = new int[] { 1, 2, 3, 4 };
		int key = 0;

//		process(nums, key, (v, k) -> {
//			try {
//				System.out.println(v / k);
//			} catch (Exception e) {
//				System.out.println("Arithematic Exception");
//			}
//		});

		process(nums, key, WrapperLambda((v, k) -> System.out.println(v / k)));

	}

	private static void process(int nums[], int key, BiConsumer<Integer, Integer> biConsumer) {
		for (int t : nums) {
			biConsumer.accept(t, key);
		}
	}

	private static BiConsumer<Integer, Integer> WrapperLambda(BiConsumer<Integer, Integer> consumer) {
		return (x, y) -> {
			try {
				consumer.accept(x, y);
			} catch (ArithmeticException e) {
				System.out.println("Exception Inside Wrapper Lambda");
			}
		};
	}

}
