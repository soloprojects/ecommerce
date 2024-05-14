package com.ecommerce.bookinventory.metadata;

import java.lang.annotation.*;

import com.ecommerce.bookinventory.validator.ISBNValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ISBNValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ISBNConstraint {
    String message() default "ISBN code must contain only numbers and dashes";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
