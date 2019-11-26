package com.lapciakbilicki.pas2.filler;

import com.github.javafaker.Faker;

import com.lapciakbilicki.pas2.model.Role.Role;
import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.repository.RoleRepository;
import com.lapciakbilicki.pas2.service.RoleService;

import javax.inject.Inject;
import java.util.*;

public class AccountFiller implements Filler<Account> {

    @Inject
    RoleService roleService;

    @Override
    public void autoFill(List<Account> destination) {
        Faker faker = new Faker(Locale.ENGLISH);
        Random random = new Random();
        String login;

        for (int i = 0; i < 20; i++) {
            login = faker.witcher().character();
            int roleType = random.nextInt(3);
            List<Role> roles = new ArrayList<>();
            roles.add(roleService.getAll().get(roleType));
            destination.add(new Account(UUID.randomUUID().toString(), login, "fill", login, true, roles));
        }
    }

}
