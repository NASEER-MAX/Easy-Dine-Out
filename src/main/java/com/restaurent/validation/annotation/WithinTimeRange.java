package com.restaurent.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.restaurent.validation.WithinTimeRangeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WithinTimeRangeValidator.class)
public @interface WithinTimeRange {

	String message() default "Start time should be between 8 AM and 10 PM and have 00 minutes";

	String start();

	String end();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
