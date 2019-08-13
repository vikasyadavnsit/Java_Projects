package org.factory;

public class Divide implements Calculate{
  
	public void calculate(double a, double b) {
		System.out.println("a/b is " + (a/b));
	}
}
