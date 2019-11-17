package com.lapciakbilicki.pas2.filler;

import com.github.javafaker.Faker;

import com.lapciakbilicki.pas2.model.account.Account;
import com.lapciakbilicki.pas2.model.account.AdminAccount;
import com.lapciakbilicki.pas2.model.account.ClientAccount;
import com.lapciakbilicki.pas2.model.account.ResourcesManagerAccount;

import javax.inject.Named;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

@Named
public class AccountFiller implements Filler<Account> {

    @Override
    public void autoFill(List<Account> destination) {
        Faker faker = new Faker(Locale.ENGLISH);
        Random random = new Random();
        int randType;
        String login;

        for(int i = 0; i < 20; i++){
            randType = random.nextInt(3);
            login = faker.witcher().character();
            if(randType == 0)
                destination.add(new AdminAccount(UUID.randomUUID().toString(), login, "fill", login, true));
            if(randType == 1)
                destination.add(new ResourcesManagerAccount(UUID.randomUUID().toString(), login, "fill", login, true));
            if(randType == 2)
                destination.add(new ClientAccount(UUID.randomUUID().toString(), login, "fill", login, true));

        }
    }

}
