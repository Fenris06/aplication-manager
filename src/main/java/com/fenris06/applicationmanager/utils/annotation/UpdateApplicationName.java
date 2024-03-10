package com.fenris06.applicationmanager.utils.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UpdateApplicationNameValidation.class)
@Documented
public @interface UpdateApplicationName {
    String message() default "{Release.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
