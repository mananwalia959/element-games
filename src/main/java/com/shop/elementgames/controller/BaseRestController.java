package com.shop.elementgames.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api")
public class BaseRestController {
	
	//catch all fallbacks with /api
	@RequestMapping(path = {"", "/{*config}/**"})
	void notmessage() {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	
	

}
