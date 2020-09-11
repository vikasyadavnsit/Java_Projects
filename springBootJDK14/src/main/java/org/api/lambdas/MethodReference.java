package org.api.lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MethodReference {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(new Person("Vikas", "Yadav", 24), new Person("Rmadevame", "golca", 12),
				new Person("Chokaye", "Hira", 69));

		perfomConditonally(people, (Person p) -> true, System.out::println);

	}

	private static void perfomConditonally(List<Person> people, Predicate<Person> predicate,
			Consumer<Person> consumer) {
		for (Person p : people) {
			if (predicate.test(p)) {
				consumer.accept(p);
			}
		}
	}
}
