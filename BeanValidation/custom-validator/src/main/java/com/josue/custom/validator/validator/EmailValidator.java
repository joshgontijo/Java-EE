/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.custom.validator.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Josue
 */
public class EmailValidator implements ConstraintValidator<Email, String> {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$", Pattern.CASE_INSENSITIVE);

    @Override
    public void initialize(Email constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(value);
        return matcher.find();
    }

}
