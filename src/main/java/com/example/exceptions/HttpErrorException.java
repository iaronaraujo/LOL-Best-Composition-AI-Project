package com.example.exceptions;

public abstract class HttpErrorException extends Exception {
	private static final long serialVersionUID = -4066596231494303846L;
	
	private String msg;
	
	public HttpErrorException(String msg) {
		this.msg = msg;
	}
	
	public String getMessage() {
		return msg;
	}
}
