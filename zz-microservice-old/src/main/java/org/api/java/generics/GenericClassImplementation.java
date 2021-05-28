package org.api.java.generics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class GenericClassImplementation<T, U, V> {

	T obj1;
	U obj2;

	GenericClassImplementation(T t, U u) {
		this.obj1 = t;
		this.obj2 = u;
	}

	void print() {
		System.out.println(obj1);
		System.out.println(obj2);
	}

	static <X> void printList(List<X> x) {
		System.err.println("Stated Printing List : ---");
		for (X temp : x) {
			System.out.print(temp + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {

		GenericClassImplementation<Integer, String, ?> o = new GenericClassImplementation<>(10, "HI");
		o.print();

		printList(Arrays.asList("1", "2", "3", "4"));
		printList(Arrays.asList(1, 2, 3, 4));

		GenericClassImplementation<String, String, ?> o1 = new GenericClassImplementation<>("Hi", "Hello");
		o1.print();

		HashSet<Integer> s = new HashSet<>();
		s.remove(1);
	}

}
