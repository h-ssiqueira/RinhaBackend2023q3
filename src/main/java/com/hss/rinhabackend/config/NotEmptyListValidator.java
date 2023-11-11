package com.hss.rinhabackend.config;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List<String>> {

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {

    }

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        return value == null || (!value.isEmpty() && value.stream().noneMatch(s -> s == null || s.isEmpty() || s.length() > 32));
    }
}

