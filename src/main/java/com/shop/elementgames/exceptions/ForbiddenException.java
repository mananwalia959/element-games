package com.shop.elementgames.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = -5016166212730429740L;
	
	public ForbiddenException() {
		this("Forbidden");
	}
	
	public ForbiddenException(String message) {
		super(message);
	}

}
