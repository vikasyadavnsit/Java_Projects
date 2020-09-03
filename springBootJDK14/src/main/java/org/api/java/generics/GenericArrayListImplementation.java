package org.api.java.generics;

public class GenericArrayListImplementation {

	public static void main(String[] args) throws IllegalAccessException {
		GenericList<Integer> li = new GenericArrayList<>(100, 70);
		li.add(10);
		li.add(23423);
		System.out.println(li.toString());

		li.delete(0);
		System.out.println(li.toString());
		li.delete(100);
	}

}
