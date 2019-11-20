package com.lapciakbilicki.pas2.repository;

import com.lapciakbilicki.pas2.filler.Filler;
import com.lapciakbilicki.pas2.filler.SportsFacilityFiller;
import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;


@ApplicationScoped
@Named
public class SportsFacilityRepository extends RepositoryAdapter<SportsFacility> {


    public SportsFacilityRepository() {
         this.setListOfItems(new ArrayList<>());
    }

    @PostConstruct
    @Inject
    public void init(SportsFacilityFiller filler){
        this.setFiller(filler);
        this.getFiller().autoFill(this.getListOfItems());
    }



    @Override
    public void update(SportsFacility item) {
        if(this.remove(item)){
            this.add(item);
        }
    }
}
