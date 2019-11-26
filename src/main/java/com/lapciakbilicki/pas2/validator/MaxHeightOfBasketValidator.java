package com.lapciakbilicki.pas2.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("maxHeightOfBasketValidator")
public class MaxHeightOfBasketValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        UIInput inputMinHeightOfBasket = (UIInput) uiComponent.getAttributes().get("minHeightOfBasket");
        double minHeightOfBasket= Double.parseDouble(inputMinHeightOfBasket.getValue().toString());
        double maxHeightOfBasket = (double)o;
        if(minHeightOfBasket > maxHeightOfBasket){
            throw new ValidatorException(new FacesMessage("Max height of basket is lower than min height of basket"));
        }
    }
}
