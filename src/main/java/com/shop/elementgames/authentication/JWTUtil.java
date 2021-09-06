package com.shop.elementgames.authentication;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.shop.elementgames.authentication.models.UserPrincipal;
import com.shop.elementgames.exceptions.BadAuthenticationException;
import com.shop.elementgames.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	@Value("${authentication.jwt-secret}")
	private String jwtSecret;
	
	public Authentication getAuthenticationFromToken(String token) {
		
		Jws<Claims> claims = null;
		
		try {
			claims = Jwts.parser().setSigningKey(Base64.getEncoder().encode(jwtSecret.getBytes()))
					.parseClaimsJws(token);			
		}catch (Exception e) {
			//all exceptions are for invalid tokens
			throw new BadAuthenticationException();
		}
		
		if(!claims.getHeader().getAlgorithm().equals(SignatureAlgorithm.HS256.getValue())) {
			throw new BadAuthenticationException();
		}
		 
		
		UUID profileId = UUID.fromString(claims.getBody().getSubject());
		
		var isAdmin = claims.getBody().get("isAdmin", Boolean.class);
		 
		GrantedAuthority authority =new SimpleGrantedAuthority(AuthConstants.ROLE_USER);
		
		Collection<GrantedAuthority> grantedAuthorities = Set.of(authority);
		if(Boolean.TRUE.equals(isAdmin)) {
			grantedAuthorities.add(new SimpleGrantedAuthority(AuthConstants.ROLE_ADMIN));
		}
		
		UserPrincipal up = new UserPrincipal(profileId);
		
		
		return new AnonymousAuthenticationToken(up.getProfileId().toString(),up, grantedAuthorities);
		
	}
	
	public String getTokenForUser(User user) {
		
		return Jwts.builder()
				  .setIssuer("app.elementgames")
				  .setSubject(user.getProfileId().toString())
				  .claim("isAdmin", user.isAdminAccount() )
				  .setExpiration(Date.from(Instant.now().plus(10, ChronoUnit.DAYS)))
				  .signWith(
				    io.jsonwebtoken.SignatureAlgorithm.HS256,
				    Base64.getEncoder().encode(jwtSecret.getBytes())
				  )
				  .compact();

	}
	
	

}
