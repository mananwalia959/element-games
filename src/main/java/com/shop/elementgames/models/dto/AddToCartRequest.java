package com.shop.elementgames.models.dto;

import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartRequest {
	
	@NotNull
	private UUID productId;
	
	@Min(value = 1)
	@Max(value = 5)
	private int count;
	
	
	
}
