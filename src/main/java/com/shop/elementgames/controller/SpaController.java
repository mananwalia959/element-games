package com.shop.elementgames.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaController {
	// redirects all paths except containind dots or starting with api
	// https://stackoverflow.com/a/48622614.
	@RequestMapping(value =  {"/{path:[^\\.]*}" , "/**/{path:^(?!api).*}/{path:[^\\.]*}"})
	public String forward(HttpServletRequest request) {
		return "forward:/index.html";
	}

}
