package com.josue.jaxrs.validation.validator;

import com.josue.jaxrs.validation.SampleMessage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Josue on 10/07/2016.
 */
public class MessageValidator implements ConstraintValidator<ValidMessage, SampleMessage> {

    private ValidMessage annotation;

    @Override
    public void initialize(ValidMessage constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(SampleMessage value, ConstraintValidatorContext context) {
        //can use annotation here to customize the behaviour
        //TODO decent exception mapper isn't possible
        return value != null &&
                value.getId() != null && value.getId() > 0 &&
                value.getText() != null && !value.getText().isEmpty();
    }
}
