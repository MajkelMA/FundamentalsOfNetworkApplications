package com.lapciakbilicki.pas2.validator;

import com.lapciakbilicki.pas2.model.account.Account;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.io.Serializable;
import java.util.List;

@FacesValidator("loginUniqueValidator")
public class LoginUniqueValidator implements Validator, Serializable {

    private final String engMessage = "Login is not unique";
    private final String plMessage = "Login nie jest unikalny";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String login = (String) o;
        List<Account> accounts = (List<Account>) uiComponent.getAttributes().get("allAccounts");
        String oldLogin = (String) uiComponent.getAttributes().get("login");
        Account accountToFind = accounts.stream()
                .filter(account -> account.getLogin().equals(login))
                .findFirst()
                .orElse(null);
        if (accountToFind != null) {
            if (oldLogin == null) {
                throw new ValidatorException(new FacesMessage(facesContext.getViewRoot().getLocale().toString().equals("en") ? engMessage : plMessage));
            } else {
                if (!accountToFind.getLogin().equals(oldLogin)) {
                    throw new ValidatorException(new FacesMessage(facesContext.getViewRoot().getLocale().toString().equals("en") ? engMessage : plMessage));
                }
            }
        }
    }

}
