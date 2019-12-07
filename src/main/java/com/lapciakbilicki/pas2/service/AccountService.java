package com.lapciakbilicki.pas2.service;


import com.lapciakbilicki.pas2.model.Role.Role;
import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.repository.AccountRepository;
import com.sun.org.apache.xpath.internal.objects.XString;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestScoped
public class AccountService extends ServiceAdapter<Account> {

    @Inject
    RoleService roleService;

    public AccountService() {
    }

    @PostConstruct
    @Inject
    public void init(AccountRepository accountRepository) {
        this.repository = accountRepository;
    }

    public boolean checkLoginUnique(String login) {
        List<Account> accounts = repository.getByCondition(
                account -> account.getLogin().equals(login)
        );
        return accounts.isEmpty();
    }

    @Override
    public boolean add(Account item) {
        if (checkLoginUnique(item.getLogin())) {
            return repository.add(item);
        } else return false;
    }

    public void updateAccount(Account item) {
        this.repository.update(item);
    }

    public Account getAccountByLogin(String login){
        return this.repository.getAll()
                .stream()
                .filter(account -> account.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }

    public List<Account> filterAccount(String input) {
        return this.repository.getAll()
                .stream()
                .filter(acc -> acc.getLogin().contains(input))
                .collect(Collectors.toList());
    }

    public void changeAccountActivity(String id) {
        Account accountToChange = this.get(id);
        if (accountToChange != null)
            accountToChange.setActive(!accountToChange.isActive());
    }

    public boolean createUserWithRoles(String[] roles, String login, String password, String fullName) {
        List<Role> usersRoles = new ArrayList<>();
        for (String role : roles) {
            usersRoles.add(roleService.get(role));
        }
        return add(new Account(UUID.randomUUID().toString(), login, password, fullName, true, usersRoles));
    }

    public void updateAccountWithRole(String id, String login, String password, String fullName, boolean active, String[] roles) {
        List<Role> usersRoles = new ArrayList<>();
        for (String role : roles) {
            usersRoles.add(roleService.get(role));
        }

        Account account = new Account(id, login, password, fullName, active, usersRoles);

        this.update(account);
    }

}
