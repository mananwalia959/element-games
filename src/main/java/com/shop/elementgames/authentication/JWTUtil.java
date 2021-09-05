package com.shop.elementgames.authentication;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.shop.elementgames.authentication.models.UserPrincipal;

@Component
public class JWTUtil {
	
	@Value("${authentication.jwt-secret}")
	private String jwtSecret;
	
	public Authentication getAuthenticationFromToken() {
		GrantedAuthority authority =new SimpleGrantedAuthority(AuthConstants.ROLE_USER);
		
		Collection<GrantedAuthority> authorities = Set.of(authority);
		Authentication authentication = new AnonymousAuthenticationToken("null", new UserPrincipal(),authorities );
		
		return authentication;
	}
	
	public String getTokenForUserPrincipal() {
		return "";
	}

}
