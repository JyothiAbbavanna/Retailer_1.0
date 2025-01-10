package com.example.exception;

public class CustomerNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6563577799001170589L;

	public CustomerNotFoundException(String message) {
		super(message);
	}
}
