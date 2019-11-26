package com.lapciakbilicki.pas2.controller;


import com.lapciakbilicki.pas2.service.AccountService;
import com.lapciakbilicki.pas2.service.RoleService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;


@Named
@RequestScoped
public class CreateAccountController implements Serializable {
    private String[] roles;
    private String login;
    private String password;
    private String fullName;

    private String message;

    @Inject
    AccountService accountService;

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
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
        if(accountService.createUserWithRoles(roles, login, password, fullName)){
            message = "Account created successfully";
        }
        else{
            message = "account did not create";
        }
    }

    public String getMessage() {
        return message;
    }
}
