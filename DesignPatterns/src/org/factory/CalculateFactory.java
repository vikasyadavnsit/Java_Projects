package org.factory;

public class CalculateFactory {

	public Calculate getCalculation(String type) {

		Calculate obj = null;

		if (type.toLowerCase().equals("add")) {
			obj = new Add();
		} else if (type.toLowerCase().equals("subtract")) {
			obj = new Subtract();
		} else if (type.toLowerCase().equals("divide")) {
			obj = new Divide();
		} else {
			System.out.println("we don't do that!");
		}

		return obj;
	}
}
