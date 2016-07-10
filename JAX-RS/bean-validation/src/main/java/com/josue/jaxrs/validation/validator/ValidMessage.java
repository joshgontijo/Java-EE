package com.josue.jaxrs.validation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Josue on 10/07/2016.
 */
@Documented
@Constraint(validatedBy = MessageValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMessage {

    String message() default "{default.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
