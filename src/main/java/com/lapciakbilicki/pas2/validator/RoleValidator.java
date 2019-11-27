package com.lapciakbilicki.pas2.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("roleValidator")
public class RoleValidator implements Validator {
    private final String engMessage = "Select role";
    private final String plMessage = "Wybierz role";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
            String[] roles = (String[])o;
            if(roles.length == 0){
                throw new ValidatorException(new FacesMessage(facesContext.getViewRoot().getLocale().toString().equals("en") ? engMessage : plMessage));
            }
    }
}
