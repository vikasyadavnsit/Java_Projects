package org.api.challanges.sapient;

public enum ProductSize {
	SMALL("S"), MEDIUM("M"), LARGE("L");

	private String size;

	ProductSize(String size) {
		this.size = size;
	}

	public String getValue() {
		return this.size;
	}
}
