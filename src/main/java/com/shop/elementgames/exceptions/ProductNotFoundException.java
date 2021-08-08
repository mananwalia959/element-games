package com.shop.elementgames.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND ,reason = "product not found" )
public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -24935800085727761L;

}
