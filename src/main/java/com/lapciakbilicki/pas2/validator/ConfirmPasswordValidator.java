package com.lapciakbilicki.pas2.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("confirmPasswordValidator")
public class ConfirmPasswordValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String password = (String)o;
        UIInput inputPassword = (UIInput)uiComponent.getAttributes().get("confirmPassword");
        String confirmPassword = inputPassword.getSubmittedValue().toString();

        if(password.length() < 8){
            throw new ValidatorException(new FacesMessage("Password is too short"));
        }

        if(!password.equals(confirmPassword)) {
            throw new ValidatorException(new FacesMessage("Password must match confirm password"));
        }
    }
}
