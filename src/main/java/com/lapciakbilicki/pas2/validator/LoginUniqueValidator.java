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

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String login = (String)o;
        List<Account> accounts = (List<Account>)uiComponent.getAttributes().get("allAccounts");
        Account accountToFind = accounts.stream()
                .filter(account -> account.getLogin().equals(login))
                .findFirst()
                .orElse(null);
        if(accountToFind != null){
            throw new ValidatorException(new FacesMessage("login is not unique"));
        }
    }

}
