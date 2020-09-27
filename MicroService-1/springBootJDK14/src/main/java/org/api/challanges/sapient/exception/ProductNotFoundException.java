package org.api.challanges.sapient.exception;

public class ProductNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 5442189032427847473L;

	public ProductNotFoundException() {
	}

	public ProductNotFoundException(String message) {
		super(message);
	}
}
