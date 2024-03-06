package com.fenris06.applicationmanager.utils.annotation;

import com.fenris06.applicationmanager.model.Status;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserStatusValidation implements ConstraintValidator<UserStatus, Enum<Status>> {

    @Override
    public boolean isValid(Enum<Status> statusEnum, ConstraintValidatorContext constraintValidatorContext) {
        if (statusEnum != null) {
            return statusEnum.equals(Status.SENT) || statusEnum.equals(Status.DRAFT);
        }
        return false;
    }
}
