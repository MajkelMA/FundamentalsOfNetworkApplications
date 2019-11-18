package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.model.account.AdminAccount;
import com.lapciakbilicki.pas2.model.account.ClientAccount;
import com.lapciakbilicki.pas2.model.account.ResourcesManagerAccount;
import com.lapciakbilicki.pas2.service.AccountService;

import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named
@ViewScoped
public class ModifyAccountController implements Serializable {
    private String message;
    private AccountService accountService;
    private String type, id, login, password, fullName, active;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    @Inject
    public void init(AccountService accountService) {
        this.type = accountService.get(requestMap.get("id")).getClass().getSimpleName();
        this.id = accountService.get(requestMap.get("id")).getId();
        this.login = accountService.get(requestMap.get("id")).getLogin();
        this.password = accountService.get(requestMap.get("id")).getPassword();
        this.fullName = accountService.get(requestMap.get("id")).getFullName();
        if (accountService.get(requestMap.get("id")).isActive() == true) {
            this.active = "true";
        } else {
            this.active = "false";
        }
        this.accountService = accountService;
    }

    public void updateAccount() {
        boolean isActive = false;
        if (this.active.equals("true")) {
            isActive = true;
        }
        if (this.type.equals("AdminAccount")) {
            this.accountService.updateAccount(new AdminAccount(UUID.fromString(this.id).toString(), login, password, fullName, isActive));
        } else if (this.type.equals("ResourcesManagerAccount")) {
            this.accountService.updateAccount(new ResourcesManagerAccount(UUID.fromString(this.id).toString(), login, password, fullName, isActive));
        } else {
            this.accountService.updateAccount(new ClientAccount(UUID.fromString(this.id).toString(), login, password, fullName, isActive));
        }
        this.message = "Success";
    }

    //<editor-fold desc="getters">
    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getActive() {
        return active;
    }
    //</editor-fold>

    //<editor-fold desc="setters">
    public void setType(String type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setActive(String active) {
        this.active = active;
    }
    //</editor-fold>
}
