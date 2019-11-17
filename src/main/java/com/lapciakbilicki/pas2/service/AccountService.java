package com.lapciakbilicki.pas2.service;


import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.repository.AccountRepository;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class AccountService extends ServiceAdapter<Account> {

    public AccountService() {
    }

    @PostConstruct
    @Inject
    public void init(AccountRepository accountRepository){
        this.repository = accountRepository;
    }

}
