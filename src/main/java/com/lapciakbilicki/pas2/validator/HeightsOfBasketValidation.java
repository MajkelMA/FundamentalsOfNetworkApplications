package com.lapciakbilicki.pas2.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HeightsOfBasketValidationImpl.class)
public @interface HeightsOfBasketValidation {
    String message() default "Sports Facility name must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
