package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.Role.Role;
import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.service.AccountService;
import sun.net.idn.StringPrep;


import javax.annotation.PostConstruct;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class AccountController implements Serializable {

    @Inject
    private AccountService accountService;
    private Account account = new Account();
    private Account accountToModify = new Account();
    private String[] roles;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    @PostConstruct
    private void init(){
        if(requestMap.containsKey("id")){
            Account account = getActiveAccount();
            accountToModify.setRoles(account.getRoles());
            accountToModify.setId(account.getId());
            accountToModify.setLogin(account.getLogin());
            accountToModify.setPassword(account.getPassword());
            accountToModify.setFullName(account.getFullName());
            accountToModify.setActive(account.isActive());

            List<Role> roles = accountToModify.getRoles();
            this.roles = new String[roles.size()];
            for(int i = 0; i < this.roles.length; i++){
                this.roles[i] = roles.get(i).getId();
            }
        }
    }

    //<editor-fold desc="getters and setter">
    public List<Account> getAll(){
        return accountService.getAll();
    }

    public Account getActiveAccount(){
        return this.accountService.get(requestMap.get("id"));
    }

    public void changeUserActive(String id){
        accountService.changeAccountActivity(id);
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccountToModify() {
        return accountToModify;
    }

    public void setAccountToModify(Account accountToModify) {
        this.accountToModify = accountToModify;
    }

    //</editor-fold>

    public void createAccount(){
        accountService.createUserWithRoles(roles, account.getLogin(), account.getPassword(), account.getFullName());
    }

    public List<Account> getAllAccounts(){
        return accountService.getAll();
    }

    public void updateAccount() {
        accountService.updateAccountWithRole(accountToModify.getId(), accountToModify.getLogin(), accountToModify.getPassword(), accountToModify.getFullName(), accountToModify.isActive(), roles);
    }

}
