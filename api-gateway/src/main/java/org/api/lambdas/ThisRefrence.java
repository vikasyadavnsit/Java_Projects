package org.api.lambdas;

import java.util.function.Consumer;

public class ThisRefrence {

	public void doProcess(int i, Consumer<Integer> p) {
		p.accept(i);
	}

	public void execute() {
		doProcess(10, i -> System.out.println("Value of i :" + i + " and this refrence : " + this));
	}

	public static void main(String[] args) {
		ThisRefrence obj = new ThisRefrence();

		obj.doProcess(10, new Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
				System.out.println("Value of i :" + t + " and this refrence : " + this);
			}

			public String toString() {
				return "To String from Anonymous Inner Class";
			}
		});

		obj.execute();
	}

	public String toString() {
		return "To String form Main Class";
	}

}
