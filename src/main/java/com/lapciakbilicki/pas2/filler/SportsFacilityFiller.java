package com.lapciakbilicki.pas2.filler;

import com.github.javafaker.Faker;
import com.lapciakbilicki.pas2.model.sportsfacility.BasketballFacility;
import com.lapciakbilicki.pas2.model.sportsfacility.Field;
import com.lapciakbilicki.pas2.model.sportsfacility.FootballFacility;
import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;

import javax.inject.Named;
import java.util.List;
import java.util.Random;
import java.util.UUID;


public class SportsFacilityFiller implements Filler<SportsFacility> {
    @Override
    public void autoFill(List<SportsFacility> destination) {
        Faker faker = new Faker();
        Random random = new Random();
        Field field;
        for(int i = 0; i < 20; i++){
            field =   new Field(random.nextDouble()*500+100, random.nextInt(30)+10, faker.elderScrolls().city());
            if(random.nextBoolean()){
                destination.add(new FootballFacility(UUID.randomUUID().toString(),
                        random.nextDouble()*50+5,
                        true,
                         field,
                         faker.witcher().location(),
                         random.nextBoolean(),
                        random.nextDouble()*5,
                        random.nextDouble()*3
                ));
            }
            else{
                destination.add(new BasketballFacility(UUID.randomUUID().toString(),
                        random.nextDouble()*50+5,
                        true,
                         field,
                         faker.witcher().location(),
                        random.nextInt(10)+2,
                        1.5,
                         random.nextDouble()*5
                        ));
            }
        }
    }
}
