package com.realestate.app;

public class NoDataFromQueryExc extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public NoDataFromQueryExc(String message) {
		super(message);
	
	}
}
