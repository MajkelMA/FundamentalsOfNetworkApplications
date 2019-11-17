package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.model.account.AdminAccount;
import com.lapciakbilicki.pas2.model.account.ClientAccount;
import com.lapciakbilicki.pas2.model.account.ResourcesManagerAccount;
import com.lapciakbilicki.pas2.service.AccountService;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.UUID;

@Named
@RequestScoped
public class CreateAccountController implements Serializable {
    private String type;
    private String login;
    private String password;
    private String fullName;

    private String message;

    @Inject
    AccountService accountService;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public void createAccount(){
        boolean result;
        switch(type){
            case "Users Admin":
                result = this.accountService.add(new AdminAccount(UUID.randomUUID().toString(), login, password, fullName, true));
                break;
            case "Resources Admin":
                result = this.accountService.add(new ResourcesManagerAccount(UUID.randomUUID().toString(), login, password, fullName, true));
                break;
            case "Client":
                result = this.accountService.add(new ClientAccount(UUID.randomUUID().toString(), login, password, fullName, true));
                break;
            default:
                result = false;;
        }
        if (result) {
           message = "Account created successfully";
        } else {
            message = "Account did not create";
        }
    }

    public String getMessage() {
        return message;
    }
}
