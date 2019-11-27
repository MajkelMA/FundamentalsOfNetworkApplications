package com.lapciakbilicki.pas2.validator;

import com.sun.org.apache.xpath.internal.objects.XString;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("maxHeightOfBasketValidator")
public class MaxHeightOfBasketValidator implements Validator {

    private final String plMessage = "Maksymalna wysokość kosza musi być większa od minimalnej";
    private final String engMessage = "Max Height of basket must be greater than min";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        UIInput inputMinHeightOfBasket = (UIInput) uiComponent.getAttributes().get("minHeightOfBasket");
        double minHeightOfBasket= Double.parseDouble(inputMinHeightOfBasket.getValue().toString());
        double maxHeightOfBasket = (double)o;
        if(minHeightOfBasket > maxHeightOfBasket){
            throw new ValidatorException(new FacesMessage(facesContext.getViewRoot().getLocale().toString().equals("en") ? engMessage : plMessage));
        }
    }
}
