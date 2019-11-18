package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.service.AccountService;

import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class AccountController implements Serializable {

    @Inject
    private AccountService accountService;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    public List<Account> getAll(){
        return accountService.getAll();
    }

    public Account getActiveAccount(){
        return this.accountService.get(requestMap.get("id"));
    }

    public void changeUserActive(String id){
        accountService.changeAccountActivity(id);
    }
}
