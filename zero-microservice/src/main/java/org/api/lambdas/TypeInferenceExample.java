package org.api.lambdas;

public class TypeInferenceExample {

	public static void main(String[] args) {
		StringLengthLambda myLambda = (String s) -> s.length();
		System.out.println(myLambda.getLength("Hello Lambda"));

		// Compiler automatically inferred the type and No of parameters of a functional
		// interface
		StringLengthLambda myLambda2 = s -> s.length();
		System.out.println(myLambda2.getLength("Hello Lambda"));

		printLambda(s -> s.length());

	}

	public static void printLambda(StringLengthLambda l) {
		System.out.println(l.getLength("Hello Lambda"));
	}

	interface StringLengthLambda {
		int getLength(String s);
	}
}
