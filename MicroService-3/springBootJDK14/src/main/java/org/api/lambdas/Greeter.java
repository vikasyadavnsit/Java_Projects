package org.api.lambdas;

public class Greeter {

	public void greet(Greeting greeting) {
		greeting.perform();
	}

	public static void main(String[] args) {
		// Using OOP to pass behavior through a thing
		Greeter obj = new Greeter();
		HelloWorldGreeting helloWorldGreeting = new HelloWorldGreeting();
		obj.greet(helloWorldGreeting);

		// Using Lambdas to directly pass in the behavior
		Greeting lambdaGreeting = () -> System.out.println("Hello World, I am performing Good");
		obj.greet(lambdaGreeting);

		// Both print the same result : This is how we execute lambda expressions. By
		// calling the interface method on it , just as it were an instance of a class
		helloWorldGreeting.perform();
		lambdaGreeting.perform();

		// Anonymous inner class -> It is implementing an interface and creating an
		// instance of it.
		Greeting innerClassGreeting = new Greeting() {
			public void perform() {
				System.out.println("Hello world, through inner class");
			}
		};

		innerClassGreeting.perform();

	}
}
