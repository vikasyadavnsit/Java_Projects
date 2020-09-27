package org.api.challanges.sapient;

public enum ProductColor {
	RED("RED"), GREEN("GREEN"), BLUE("BLUE"), CYAN("CYAN");

	private String color;

	ProductColor(String color) {
		this.color = color;
	}

	public String getValue() {
		return this.color;
	}
}
