package com.example.exceptions;

public class GGHttpErrorException extends HttpErrorException {
	private static final long serialVersionUID = 3977033535525218448L;

	public GGHttpErrorException(String msg) {
		super(msg);
	}
	
	public GGHttpErrorException() {
		super("");
	}
}
