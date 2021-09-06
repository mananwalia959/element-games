package com.shop.elementgames.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaController {
	// redirects all paths except containing dots or leftover from other controllers
	// https://stackoverflow.com/a/56187917
	@RequestMapping(value =  "/**/{path:[^\\.]*}")
	public String forward(HttpServletRequest request) {
		return "forward:/index.html";
	}

}
