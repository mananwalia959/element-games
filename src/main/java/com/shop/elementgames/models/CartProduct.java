package com.shop.elementgames.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "cart_product")
public class CartProduct {
	
	@Id
	@Column(name = "database_id")
	//only used for database
	private UUID databaseId;
	
	@Column(name = "product_id")
	private UUID productId;

	@Column(name = "profile_id")
	private UUID profileId;
	
	@Column(name = "count")
	private int count;  

}
