package com.example.exceptions;

public class RiotHttpErrorException extends HttpErrorException {
	private static final long serialVersionUID = 8168668955613305098L;
	
	public RiotHttpErrorException(String msg) {
		super(msg);
	}
	
	public RiotHttpErrorException() {
		super("");
	}
}
