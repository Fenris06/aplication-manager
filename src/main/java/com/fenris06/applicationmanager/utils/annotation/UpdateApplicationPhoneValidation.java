package com.fenris06.applicationmanager.utils.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateApplicationPhoneValidation implements ConstraintValidator<UpdateApplicationPhone, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s != null) {
            Pattern pattern = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
            Matcher matcher = pattern.matcher(s);
            return matcher.matches();
        }
        return true;
    }
}
