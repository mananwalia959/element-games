package com.shop.elementgames.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired private JWTAuthenticationFilter authFilter;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.addFilterAt(authFilter, BasicAuthenticationFilter.class)
		.formLogin().disable().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        // order of matchers matters here
        .antMatchers(HttpMethod.OPTIONS).permitAll()
        .antMatchers("/api/products/**").permitAll()
        .antMatchers("/api/**").hasAuthority(AuthConstants.ROLE_USER)       
        ;
		
	}

}
