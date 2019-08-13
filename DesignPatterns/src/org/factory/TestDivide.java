package org.factory;

import java.util.Scanner;

public class TestDivide {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.println("Enter First Number :");
		double num1 = input.nextDouble();

		System.out.println("Enter Second Number :");
		double num2 = input.nextDouble();

		System.out.println("Enter Calculation TYpe (add, subtract, divide) :");
		CalculateFactory factory = new CalculateFactory();
		Calculate obj = factory.getCalculation(input.next());
		obj.calculate(num1, num2);
	}
}
