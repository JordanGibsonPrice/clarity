package com.clarity.beans;

public class ACException extends RuntimeException {
	
	public ACException(String message, Exception cause) {
		super(message,cause);
	}
	
	public ACException(String message) {
		super(message);
	}

}
