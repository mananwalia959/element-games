package com.shop.elementgames.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.elementgames.authentication.models.UserPrincipal;
import com.shop.elementgames.models.CartProduct;
import com.shop.elementgames.models.MyProfile;
import com.shop.elementgames.models.User;
import com.shop.elementgames.repository.UserRepository;

@RestController
@RequestMapping(path = "/api/myprofile")
public class MyProfileController {
	
	@Autowired private UserRepository userRepository;
	
	private Logger logger = LoggerFactory.getLogger(MyProfileController.class);
	
	@GetMapping("")
	public MyProfile getAllProducts(@AuthenticationPrincipal UserPrincipal userPrincipal) {
		UUID profileId = userPrincipal.getProfileId();
		logger.debug("getting profile for {}" ,profileId);
		
		User user = userRepository.findById(profileId).orElseThrow();
		
		return new MyProfile(user.getEmail(),user.getName());
	}
	
	@GetMapping("/cart")
	public List<CartProduct> getCart() {
		return new ArrayList<>();
	}

}
