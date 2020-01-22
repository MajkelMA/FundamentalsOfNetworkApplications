package com.lapciakbilicki.pas2.validator;

import com.lapciakbilicki.pas2.model.sportsfacility.BasketballFacility;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HeightsOfBasketValidationImpl implements ConstraintValidator<HeightsOfBasketValidation, BasketballFacility> {

    @Override
    public boolean isValid(BasketballFacility basketballFacility, ConstraintValidatorContext constraintValidatorContext) {
        return basketballFacility.getMaxHeightOfBasket() >= basketballFacility.getMinHeightOfBasket();
    }
}
