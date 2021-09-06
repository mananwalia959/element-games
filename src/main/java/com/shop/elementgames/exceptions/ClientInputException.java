package com.shop.elementgames.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClientInputException extends RuntimeException {

	private static final long serialVersionUID = -2408362365634997584L;

	public ClientInputException() {
		this("Bad Request");
	}
	
	public ClientInputException(String message) {
		super(message);
	}

	
}
