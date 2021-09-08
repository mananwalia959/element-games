package com.shop.elementgames.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.elementgames.authentication.JWTUtil;
import com.shop.elementgames.authentication.models.JwtResponse;
import com.shop.elementgames.authentication.models.LoginRequest;
import com.shop.elementgames.authentication.models.RegisterRequest;
import com.shop.elementgames.authentication.models.RegisterResponse;
import com.shop.elementgames.exceptions.ForbiddenException;
import com.shop.elementgames.models.User;
import com.shop.elementgames.repository.UserRepository;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthenticationController {
	
	@Autowired private JWTUtil jwtutils;
	
	@Autowired private PasswordEncoder passwordEncoder;
	@Autowired private UserRepository userRepository;
	
	@PostMapping("/login")
	public JwtResponse login(@RequestBody @Valid LoginRequest loginRequest) {
		
		User user = userRepository.findOneByEmail(loginRequest.getEmail())
				.orElseThrow(ForbiddenException::new);
		
		var isPasswordCorrect = passwordEncoder.matches(loginRequest.getPassword(), user.getEncodedPassword());
		
		if(!isPasswordCorrect) {
			throw new ForbiddenException();
		}
		
		var token = jwtutils.getTokenForUser(user);
		
		return new JwtResponse(token);
	}

	
	@PostMapping("/register")
	public RegisterResponse registerNewUser(@RequestBody RegisterRequest registerRequest) {
		
		User user = new User();
		user.setProfileId(UUID.randomUUID());
		user.setName(registerRequest.getName());
		user.setEmail(registerRequest.getEmail());
		
		var encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
		user.setEncodedPassword(encodedPassword);
		
		user.setAdminAccount(false);
		
		var createdProfileId = userRepository.save(user).getProfileId();
		
		return new RegisterResponse(createdProfileId);
	}
}
