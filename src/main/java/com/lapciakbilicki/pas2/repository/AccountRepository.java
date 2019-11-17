package com.lapciakbilicki.pas2.repository;

import com.lapciakbilicki.pas2.filler.AccountFiller;
import com.lapciakbilicki.pas2.model.account.Account;
import com.sun.deploy.panel.AbstractRadioPropertyGroup;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

@ApplicationScoped
@Named
public class AccountRepository extends RepositoryAdapter<Account>{


    public AccountRepository(){
        this.setListOfItems(new ArrayList<>());
    }

    @PostConstruct
    @Inject
    public void init(AccountFiller accountFiller){
        this.setFiller(accountFiller);
        this.getFiller().autoFill(this.getAll());
    }

    @Override
    public void update(Account item) {
        Account accountToUpdate = this.get(item.getId());
        if(accountToUpdate != null){
            accountToUpdate.copyAttributionsWithoutId(item);
        }
    }
}
