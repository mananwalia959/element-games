package com.shop.elementgames.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "user")
public class User {
	
	@Id
	@Column(name = "profile_id")
	private UUID profileId;
	
	@Column(name = "encoded_password")
	private String encodedPassword;
	
	@Column(name = "is_admin_account")
	private boolean adminAccount;
	
	@Column(name = "name")
	private String name;
	
	@Column(unique = true,name = "email")
	private String email;
}
