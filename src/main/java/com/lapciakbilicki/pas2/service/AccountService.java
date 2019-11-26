package com.lapciakbilicki.pas2.service;


import com.lapciakbilicki.pas2.model.Role.Role;
import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.repository.AccountRepository;
import com.sun.org.apache.xpath.internal.objects.XString;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class AccountService extends ServiceAdapter<Account> {

    @Inject
    RoleService roleService;

    public AccountService() {
    }

    @PostConstruct
    @Inject
    public void init(AccountRepository accountRepository){
        this.repository = accountRepository;
    }

    @Override
    public boolean add(Account item){
        List<Account> accounts = repository.getByCondition(
                account -> account.getLogin().equals(item.getLogin())
        );
        if(accounts.isEmpty()) {
            return repository.add(item);
        }
        else return false;
    }

    public void updateAccount(Account item) {
        this.repository.update(item);
    }

    public void changeAccountActivity(String id){
        Account accountToChange = this.get(id);
        if(accountToChange != null)
            accountToChange.setActive(!accountToChange.isActive());
    }

    public boolean createUserWithRoles(String[] roles, String login, String password, String fullName){
        List<Role> usersRoles = new ArrayList<>();
        for(String role : roles){
            usersRoles.add(roleService.get(role));
        }
        return add(new Account(UUID.randomUUID().toString(), login, password, fullName, true, usersRoles));
    }

    public void updateAccountWithRole(String id, String login, String password, String fullName, boolean active, String[] roles) {
        List<Role> usersRoles = new ArrayList<>();
        for(String role : roles){
            usersRoles.add(roleService.get(role));
        }

        Account account = new Account(id, login, password, fullName, active, usersRoles);

        this.update(account);
    }

}
