package com.lapciakbilicki.pas2.filler;

import com.github.javafaker.Faker;

import com.lapciakbilicki.pas2.model.Role.Role;
import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.service.RoleService;
import java.io.Serializable;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

public class AccountFiller implements Filler<Account>, Serializable {

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

        destination.add(new Account(
                UUID.randomUUID().toString(),
                "resource-admin",
                "fill",
                "resource-admin",
                true,
                roleService.getAll().stream()
                        .filter(role -> role.getName().equals("Resources_Admin"))
                        .collect(Collectors.toList()))
        );

        destination.add(new Account(
                UUID.randomUUID().toString(),
                "user-admin",
                "fill",
                "user-admin",
                true,
                roleService.getAll().stream()
                        .filter(role -> role.getName().equals("User_Admin"))
                        .collect(Collectors.toList()))
        );

        destination.add(new Account(
                UUID.randomUUID().toString(),
                "client",
                "fill",
                "client",
                true,
                roleService.getAll().stream()
                        .filter(role -> role.getName().equals("Client"))
                        .collect(Collectors.toList()))
        );

        destination.add(new Account(
                UUID.randomUUID().toString(),
                "client2",
                "fill",
                "client2",
                true,
                roleService.getAll().stream()
                        .filter(role -> role.getName().equals("Client"))
                        .collect(Collectors.toList()))
        );
    }

}
