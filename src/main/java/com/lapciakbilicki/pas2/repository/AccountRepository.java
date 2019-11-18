package com.lapciakbilicki.pas2.repository;

import com.lapciakbilicki.pas2.filler.AccountFiller;
import com.lapciakbilicki.pas2.model.account.Account;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;

@ApplicationScoped
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
        if(this.remove(item)){
            if (item.getClass().getSimpleName().equals("AdminAccount")) {
                this.add(item);
            } else if (item.getClass().getSimpleName().equals("ResourcesManagerAccount")) {
                this.add(item);
            } else {
                this.add(item);
            }
        }
    }
}
