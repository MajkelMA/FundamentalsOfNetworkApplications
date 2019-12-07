package com.lapciakbilicki.pas2.beans;

import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.service.AccountService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    private AccountService accountService;

    private String username,
            password,
            message;
    private Account account = null;

    public String login() {
        this.message = null;
        this.account = this.accountService.getAccountByLogin(this.username);
        if (this.account != null
                && this.username.equals(this.account.getLogin())
                && this.password.equals(this.account.getPassword())) {
            return "/faces/index.xhtml";
        } else {
            this.message = "Invalid login or password";
            return "/faces/login/login.xhtml";
        }
    }

    //<editor-fold desc="getters and setter">

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //</editor-fold>
}