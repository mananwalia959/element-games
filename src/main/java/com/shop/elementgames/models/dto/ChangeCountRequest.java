package com.shop.elementgames.models.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeCountRequest {
	@Min(value = 1)
	@Max(value = 5)
	private int count;
}
