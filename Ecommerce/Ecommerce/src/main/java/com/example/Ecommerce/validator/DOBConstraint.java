package com.example.Ecommerce.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {DOBValidator.class}
)
public @interface DOBConstraint {
    String message() default "Invalid date of birth.";

    int min();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
