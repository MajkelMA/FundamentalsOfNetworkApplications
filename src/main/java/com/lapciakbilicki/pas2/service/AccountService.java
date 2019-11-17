package com.lapciakbilicki.pas2.service;


import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.repository.AccountRepository;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
public class AccountService extends ServiceAdapter<Account> {


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
        if(accounts.isEmpty())
            return repository.add(item);
        else return false;
    }

}
