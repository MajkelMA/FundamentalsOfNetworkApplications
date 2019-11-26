package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.Role.Role;
import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.service.AccountService;
import sun.net.idn.StringPrep;

import javax.faces.annotation.RequestParameterMap;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.management.relation.RoleStatus;
import java.io.Serializable;
import java.util.*;

@Named
@ViewScoped
public class ModifyAccountController implements Serializable {
    private String message;
    private AccountService accountService;
    private String id, login, password, fullName, active;
    private String[] roles;
    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    @Inject
    public void init(AccountService accountService) {
        List<Role> roles = accountService.get(requestMap.get("id")).getRoles();
        String [] roleIds = new String[roles.size()];
        for(int i = 0; i < roleIds.length; i++){
            roleIds[i] = roles.get(i).getId();
        }

        this.roles = roleIds;
        this.id = accountService.get(requestMap.get("id")).getId();
        this.login = accountService.get(requestMap.get("id")).getLogin();
        this.password = accountService.get(requestMap.get("id")).getPassword();
        this.fullName = accountService.get(requestMap.get("id")).getFullName();
        if (accountService.get(requestMap.get("id")).isActive()) {
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
        accountService.updateAccountWithRole(id, login, password, fullName, isActive, roles);
    }

    public String getAllRole(){
        return Arrays.toString(roles);
    }

    //<editor-fold desc="getters">
    public String[] getRoles(){
        return this.roles;
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
    public void setRoles(String[] roles){
        this.roles = roles;
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
