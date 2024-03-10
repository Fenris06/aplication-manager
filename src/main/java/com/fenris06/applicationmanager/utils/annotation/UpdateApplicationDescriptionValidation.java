package com.fenris06.applicationmanager.utils.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UpdateApplicationDescriptionValidation implements ConstraintValidator<UpdateApplicationDescription, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s != null) {
            if (s.isEmpty() || s.isBlank()) {
                return false;
            }
            if (s.length() < 3 || s.length() > 500) {
                return false;
            }
        }
        return true;
    }
}
