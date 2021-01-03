package com.api.readingisgood.error.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;

@Constraint(validatedBy = ValidInputValidator.class)
@Target({METHOD, CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidInput {
    String message() default "Invalid input";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
