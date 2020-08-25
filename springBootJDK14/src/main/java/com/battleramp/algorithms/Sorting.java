package com.battleramp.algorithms;

import java.util.Arrays;

public class Sorting {

	public static void main(String[] args) {
		int arr[] = new int[] { 1, 4, 100, 2, -6, -8, 70, 5, 2, -10, -5, 05 };

		countingSort(arr);
		// radixSort(arr);
		// print(arr);
	}

	static void print(int[] arr) {
		Arrays.stream(arr).forEach(x -> System.out.print(x + " "));
	}

	static void radixSort(int[] arr) {
		int max = Arrays.stream(arr).max().getAsInt();
		for (int i = 1; max / i > 0; i *= 10) {
			radixCountingSort(arr, i);
		}
	}

	static void radixCountingSort(int[] arr, int radix) {
		int min = Arrays.stream(arr).min().getAsInt();
		int size = 20;
		int output[] = new int[arr.length];
		int count[] = new int[20];

		for (int i = 0; i < arr.length; i++)
			++count[((arr[i] - min) / radix) % 10];

		for (int i = 1; i < size; i++)
			count[i] += count[i - 1];

		for (int i = arr.length - 1; i >= 0; i--)
			output[--count[((arr[i] - min) / radix) % 10]] = arr[i];

		for (int i = 0; i < arr.length; i++)
			arr[i] = output[i];
	}

	static void countingSort(int[] arr) {
		int min = Arrays.stream(arr).min().getAsInt();
		int max = Arrays.stream(arr).max().getAsInt();
		int size = max - min + 1;
		int output[] = new int[arr.length];
		int count[] = new int[size];

		for (int i = 0; i < arr.length; i++)
			++count[arr[i] - min];

		for (int i = 1; i < size; i++)
			count[i] += count[i - 1];

		for (int i = arr.length - 1; i >= 0; i--)
			output[--count[arr[i] - min]] = arr[i];

		for (int i = 0; i < arr.length; i++)
			arr[i] = output[i];
	}
}
