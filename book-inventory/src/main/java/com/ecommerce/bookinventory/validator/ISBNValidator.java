package com.ecommerce.bookinventory.validator;

import com.ecommerce.bookinventory.metadata.ISBNConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ISBNValidator implements ConstraintValidator<ISBNConstraint, String> {

    @Override
    public void initialize(ISBNConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String isbnCode, ConstraintValidatorContext constraintValidatorContext) {
        // Validate ISBN code format
        return isbnCode != null && isbnCode.matches("^[0-9-]+$");
    }
}