package test;

import java.util.*;

class Test2 {

	static void printFreq(Integer arr[]) {
		TreeMap<Integer, Integer> hmap = new TreeMap<Integer, Integer>();

		Arrays.asList(arr).stream().forEach(e -> {

			if (hmap.get(e) == null)
				hmap.put(e, 1);
			else
				hmap.put(e, hmap.get(e) + 1);

		});

		for (Map.Entry m : hmap.entrySet())
			System.out.println("Frequency of " + m.getKey() + " is " + m.getValue());
	}

	// Driver method to test above method
	public static void main(String[] args) {
		Integer arr[] = { 10, 34, 5, 10, 3, 5, 10 };
		printFreq(arr);
	}
}