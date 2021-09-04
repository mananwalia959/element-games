package com.shop.elementgames.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.elementgames.models.CartProduct;
import com.shop.elementgames.models.MyProfile;

@RestController
@RequestMapping(path = "/api/myprofile")
public class MyProfileController {
	
	@GetMapping("")
	public MyProfile getAllProducts() {
		return new MyProfile();
	}
	
	@GetMapping("/cart")
	public List<CartProduct> getCart() {
		return new ArrayList<>();
	}

}
