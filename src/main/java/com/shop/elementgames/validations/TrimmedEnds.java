package com.shop.elementgames.validations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { TrimmedEndsValidator.class })
@Retention(RetentionPolicy.RUNTIME)
public @interface TrimmedEnds {
	String message() default "Must not have spaces at starting at ends";
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
