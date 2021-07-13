package com.example.jugalbeats.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends Exception{
	private static final long serialVersionUID = -12231231231L;
	
	public UnauthorizedException(String message) {
		super(message);
	}

	public UnauthorizedException(String message,Throwable cause) {
		super(message,cause);
	}
}
