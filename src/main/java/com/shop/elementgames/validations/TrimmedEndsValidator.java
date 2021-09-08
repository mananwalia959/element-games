package com.shop.elementgames.validations;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TrimmedEndsValidator implements ConstraintValidator<TrimmedEnds, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		// if null, pass since out of scope, for this case use @NotNull or @NotEmpty
		if(Objects.isNull(value))
			return true;
		
		if(value.startsWith(" ") || value.endsWith(" "))
			return false;
		return true;
		
	}

}
