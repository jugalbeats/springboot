package com.example.jugalbeats.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
 
	private static final long serialVersionUID = -8032492256344059024L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}