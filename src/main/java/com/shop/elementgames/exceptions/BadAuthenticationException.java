package com.shop.elementgames.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED ,reason = "please provide a valid token" )

public class BadAuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 5857969556517862986L;
	
}
