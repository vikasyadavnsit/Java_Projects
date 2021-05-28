package org.api.java.basics;

public class VariableArguments {

	public VariableArguments() {
	}
	
	VariableArguments(int a ){	
	}
	
	public static void main(String[] args) {
		sum(23, 23, 42, 34, 24, 2, 4, 23, 42, 4);
	}

	public static void sum(int... arguments) {
		for (int i = 0; i < arguments.length; i++) {
			System.out.println(arguments[i]);
		}

		for (int a : arguments) {
			System.out.println("Digit : " + a);
		}
	}

}
