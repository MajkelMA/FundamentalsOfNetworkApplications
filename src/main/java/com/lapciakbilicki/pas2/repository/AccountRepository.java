package com.lapciakbilicki.pas2.repository;

import com.lapciakbilicki.pas2.filler.AccountFiller;
import com.lapciakbilicki.pas2.model.account.Account;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;

@ApplicationScoped
public class AccountRepository extends RepositoryAdapter<Account> implements Serializable {

    public AccountRepository() {
        this.setListOfItems(Collections.synchronizedList(new ArrayList<>()));
    }

    @Inject
    private AccountFiller accountFiller;
    
    @PostConstruct
    public void init() {
        this.setFiller(accountFiller);
        this.getFiller().autoFill(this.getAll());
    }

//    @PostConstruct
//    @Inject
//    public void init(AccountFiller accountFiller) {
//        this.setFiller(accountFiller);
//        this.getFiller().autoFill(this.getAll());
//    }
    @Override
    public void update(Account item) {
        Account accountToUpdate = this.get(item.getId());
        if (accountToUpdate != null) {
            accountToUpdate.setActive(item.isActive());
            accountToUpdate.setFullName(item.getFullName());
            accountToUpdate.setLogin(item.getLogin());
            accountToUpdate.setPassword(item.getPassword());
            accountToUpdate.setRoles(item.getRoles());
        }
    }
}
