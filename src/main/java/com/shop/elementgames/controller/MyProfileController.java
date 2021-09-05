package com.shop.elementgames.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.elementgames.authentication.models.UserPrincipal;
import com.shop.elementgames.models.CartProduct;
import com.shop.elementgames.models.MyProfile;

@RestController
@RequestMapping(path = "/api/myprofile")
public class MyProfileController {
	
	private Logger logger = LoggerFactory.getLogger(MyProfileController.class);
	
	@GetMapping("")
	public MyProfile getAllProducts(@AuthenticationPrincipal UserPrincipal userPrincipal) {
		logger.info("{}" ,userPrincipal);
		return new MyProfile();
	}
	
	@GetMapping("/cart")
	public List<CartProduct> getCart() {
		return new ArrayList<>();
	}

}
