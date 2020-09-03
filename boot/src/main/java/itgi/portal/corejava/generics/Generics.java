package itgi.portal.corejava.generics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Generics<T, U, V> {

	T obj1;
	U obj2;

	Generics(T t, U u) {
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

		Generics<Integer, String, ?> o = new Generics<>(10, "HI");
		o.print();

		printList(Arrays.asList("1", "2", "3", "4"));
		printList(Arrays.asList(1, 2, 3, 4));

		Generics<String, String, ?> o1 = new Generics<>("Hi", "Hello");
		o1.print();

		HashSet<Integer> s = new HashSet<>();
		s.remove(1);
	}

}
