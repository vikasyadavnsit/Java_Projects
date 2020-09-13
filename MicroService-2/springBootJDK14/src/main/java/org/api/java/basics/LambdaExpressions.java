package org.api.java.basics;

public class LambdaExpressions {

	public void greet(MyLambda fun) {
		fun.foo();
	}
	
	public static void main(String[] args) {
	  LambdaExpressions obj = new LambdaExpressions();	
	  MyLambda fun = () -> System.out.println("Hello World");
	  obj.greet(fun);
	}

}

interface MyLambda {
	void foo();
}

