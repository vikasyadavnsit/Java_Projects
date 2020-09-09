package org.api.design.patterns.creational;

import org.api.wrapper.patterns.PhoneWrapper;

public class BuilderDesignPattern {
	public static void main(String[] args) {
		// Using Builder Design Pattern
		PhoneWrapper wrapper = PhoneWrapper.builder().buildVersion("2342").build();
		System.out.println(wrapper);

		// Withou Using any pattern : It is still compatible
		PhoneWrapper wrapper2 = new PhoneWrapper();
		wrapper2.setBuildVersion("234243");
		System.out.println(wrapper2);

	}
}
