package org.api.lambdas;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class StandardFunctionalInterfaceExample {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(new Person("Vikas", "Yadav", 24), new Person("Rmadevame", "golca", 12),
				new Person("Chokaye", "Hira", 69));

		Collections.sort(people, (Person o1, Person o2) -> o1.getLastName().compareTo(o2.getLastName()));

		// print all people
		perfomConditonally(people, (Person p) -> true, t -> System.out.println(t));

		perfomConditonally(people, (Person p) -> p.getLastName().toLowerCase().startsWith("g"),
				t -> System.out.println(t));

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
