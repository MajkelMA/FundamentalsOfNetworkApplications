package com.lapciakbilicki.pas2.repository;

import com.lapciakbilicki.pas2.filler.Filler;
import com.lapciakbilicki.pas2.filler.SportsFacilityFiller;
import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;


@ApplicationScoped
@Named
public class SportsFacilityRepository extends RepositoryAdapter<SportsFacility> {


    /** TODO CHANGE **/

    public SportsFacilityRepository() {

    }



    @Override
    public void update(SportsFacility item) {
        SportsFacility sportsFacility = this.get(item.getId());
        if(sportsFacility != null){
            sportsFacility.copyAttributionsWithoutId(item);
        }
    }
}
