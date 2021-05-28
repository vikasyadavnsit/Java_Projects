package org.api.lambdas;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Unit1Excercise {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(new Person("Vikas", "Yadav", 24), new Person("Rmadevame", "golca", 12),
				new Person("Chokaye", "Hira", 69));

		// Sort List of people by last name
//		Collections.sort(people, new Comparator<Person>() {
//			public int compare(Person o1, Person o2) {
//				return o1.getLastName().compareTo(o2.getLastName());
//			};
//		});

		Collections.sort(people, (Person o1, Person o2) -> o1.getLastName().compareTo(o2.getLastName()));

		// print all people
		printConditionally(people, (Person p) -> true);

		// print all people whoes last name starts with 'g'
//		printConditionally(people, new Condition() {
//			@Override
//			public boolean test(Person p) {
//				return p.getLastName().toLowerCase().startsWith("g");
//			}
//		});

		printConditionally(people, (Person p) -> p.getLastName().toLowerCase().startsWith("g"));

	}

//	private static void printConditionally(List<Person> people, Condition condition) {
//		for (Person p : people) {
//			if (condition.test(p)) {
//				System.out.println(p.toString());
//			}
//		}
//	}

	private static void printConditionally(List<Person> people, Predicate<Person> predicate) {
		for (Person p : people) {
			if (predicate.test(p)) {
				System.out.println(p.toString());
			}
		}
	}

	interface Condition {
		boolean test(Person p);
	}

}
