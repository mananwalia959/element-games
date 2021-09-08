package com.shop.elementgames.authentication.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.shop.elementgames.validations.TrimmedEnds;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
	
	@NotNull
	@Email
	@TrimmedEnds
	@Size(min = 3)
	private String email;
	
	@NotBlank
	@TrimmedEnds
	@Size(min = 3)
	private String name;
	
	@NotBlank
	@TrimmedEnds
	@Size(min = 3)
	private String password;
	
}
