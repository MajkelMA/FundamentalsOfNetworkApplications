package com.lapciakbilicki.pas2.auth;

import com.lapciakbilicki.pas2.model.Role.Role;
import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.service.AccountService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.*;

@ApplicationScoped
public class InMemoryIdentityStore implements IdentityStore {

    @Inject
    AccountService accountService;

    @Override
    public CredentialValidationResult validate(Credential credential){
        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;

        Account account = accountService.getAccountByLoginAndPassword(login.getCaller(), login.getPasswordAsString());
        if(account != null){
            List<Role> roles = account.getRoles();
            Set<String> result = new HashSet<>();
            for(Role role : roles){
                result.add(role.getName());
            }
            return new CredentialValidationResult(account.getLogin(), result);
        }
        return CredentialValidationResult.NOT_VALIDATED_RESULT;
    }
}
