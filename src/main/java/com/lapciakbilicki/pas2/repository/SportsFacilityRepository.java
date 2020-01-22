package com.lapciakbilicki.pas2.repository;

import com.lapciakbilicki.pas2.filler.SportsFacilityFiller;
import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;

@ApplicationScoped
@Named
public class SportsFacilityRepository extends RepositoryAdapter<SportsFacility> implements Serializable {

    @Inject
    private SportsFacilityFiller filler;

    public SportsFacilityRepository() {
        this.setListOfItems(Collections.synchronizedList(new ArrayList<>()));
    }

    @PostConstruct
    public void init() {
        this.setFiller(filler);
        this.getFiller().autoFill(this.getListOfItems());
    }

    @Override
    public boolean update(SportsFacility item) {
        SportsFacility sportsFacility = this.get(item.getId());
        if (sportsFacility != null) {
            sportsFacility.copyAttributionsWithoutId(item);
            return true;
        }
        return false;
    }
}
