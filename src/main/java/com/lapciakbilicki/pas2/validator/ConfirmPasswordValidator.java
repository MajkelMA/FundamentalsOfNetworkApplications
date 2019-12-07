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
    private final String engMessage = "Password must match confirm password";
    private final String plMessage = "Podane hasła nie są identyczne";
    private final String tooShortEngMessage = "Password is too short";
    private final String tooShortPlMessage = "Hasło jest za krótkie";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String password = (String) o;
        UIInput inputPassword = (UIInput) uiComponent.getAttributes().get("confirmPassword");
        String confirmPassword = inputPassword.getSubmittedValue().toString();

        if (password.length() < 8) {
            throw new ValidatorException(new FacesMessage(facesContext.getViewRoot().getLocale().toString().equals("en") ? tooShortEngMessage : tooShortPlMessage));
        }

        if (!password.equals(confirmPassword)) {
            throw new ValidatorException(new FacesMessage(facesContext.getViewRoot().getLocale().toString().equals("en") ? engMessage : plMessage));
        }
    }
}
