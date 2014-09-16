package com.andriyprokip.sombrashop.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({TYPE})
@Retention(RUNTIME)

@Constraint(validatedBy = {UniqueUserValidator.class})
public @interface UniqueUser {
	
	String message();
	
	Class<?>[] groups() default { };
	
	Class<? extends Payload>[] payload() default { };

}
