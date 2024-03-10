package com.fenris06.applicationmanager.utils.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UpdateApplicationNameValidation implements ConstraintValidator<UpdateApplicationName, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s != null) {
            if (s.isEmpty() || s.isBlank()) {
                return false;
            }
            if (s.length() < 3 || s.length() > 100) {
                return false;
            }
        }
        return true;
    }
}
