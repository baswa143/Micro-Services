package com.example.exceptions;

public class NoProductFoundException extends RuntimeException{
	
	public NoProductFoundException(String msg) {
		super(msg);
	}

}
