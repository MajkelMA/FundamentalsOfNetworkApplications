package com.lapciakbilicki.pas2.model.account;

import com.lapciakbilicki.pas2.model.IsIdentified;
import com.lapciakbilicki.pas2.model.Role.Role;

import java.util.List;
import java.util.Objects;


public class Account implements IsIdentified {
    private String id;
    private String login;
    private String password;
    private String fullName;
    private boolean active;
    private List<Role> roles;

    public Account(String id, String login, String password, String fullName, boolean active, List<Role> roles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.active = active;
        this.roles = roles;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void copyAttributionsWithoutId(Account account) {
        this.login = account.login;
        this.password = account.password;
        this.fullName = account.fullName;
        this.active = account.active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Account account = (Account) o;
        return this.id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
