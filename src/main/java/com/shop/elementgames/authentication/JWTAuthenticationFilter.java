package com.shop.elementgames.authentication;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shop.elementgames.exceptions.BadAuthenticationException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter   {
	
	@Autowired private JWTUtil jwtUtil;
	

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		try {
			
			Authentication authentication = getAuthentication(request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		catch (BadAuthenticationException e) {
		}
		chain.doFilter(request, response);
		
	}


	private Authentication getAuthentication(HttpServletRequest request) {
		String token = getTokenFromAuthorizationHeader(request);
		return jwtUtil.getAuthenticationFromToken(token);
		
	}


	private String getTokenFromAuthorizationHeader(HttpServletRequest request) {
		String headerValue = Optional.ofNullable(request.getHeader(AuthConstants.TOKEN_HEADER))
				.orElseThrow(BadAuthenticationException::new);
		
		if (!headerValue.startsWith(AuthConstants.TOKEN_HEADER_PREFIX))
			throw new BadAuthenticationException();
		
		return headerValue.substring(AuthConstants.TOKEN_HEADER_PREFIX.length());
	}



	

}
