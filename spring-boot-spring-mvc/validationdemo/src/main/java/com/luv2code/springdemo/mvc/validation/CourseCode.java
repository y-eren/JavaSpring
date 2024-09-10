package com.luv2code.springdemo.mvc.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = CourseCodeConstraintValidator.class) // bu bizim helper classımızdır ve belirli bir businees rulesi oluşturmak için kullanılır
@Target( {ElementType.METHOD, ElementType.FIELD}) // bu notasyonu nereye uygulacaksın
@Retention(RetentionPolicy.RUNTIME) // process it at runtime
public @interface CourseCode {

	// define default course code
	public String value() default "LUV";
	// define default error message
	public String message() default "must start with LUV";
	// define default groups
	public Class<?>[] groups() default {};
	// define default payloads
	public Class<? extends Payload>[] payload() default {};
}


