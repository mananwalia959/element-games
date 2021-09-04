package com.shop.elementgames.authentication;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.shop.elementgames.authentication.models.UserPrincipal;

@Component
public class JWTUtil {
	
	@Value("${authentication.jwt-secret}")
	private String jwtSecret;
	
	public UserPrincipal getUserPrincipalFromJwtUtil() {
		return null;
	}
	
	public String getTokenForUserPRincipal() {
		return "";
	}
	
	@PostConstruct
	void test() {
		System.out.println(jwtSecret);
	}

}
