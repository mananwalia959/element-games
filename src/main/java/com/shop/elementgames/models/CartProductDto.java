package com.shop.elementgames.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//dto for populatedCartProduct
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDto {
	
	private Product product;
	private int count;

}
