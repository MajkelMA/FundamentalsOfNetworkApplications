package com.lapciakbilicki.pas2.service;

import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import com.lapciakbilicki.pas2.repository.SportsFacilityRepository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

public class SportsFacilityService extends ServiceAdapter<SportsFacility> {

    public SportsFacilityService(){

    }

    @PostConstruct
    @Inject
    public void init(SportsFacilityRepository sportsFacilityRepository){
        repository = sportsFacilityRepository;
    }

    @Override
    public boolean add(SportsFacility item){
        List<SportsFacility> items = repository.getByCondition(
                sportsFacility -> sportsFacility.getName().equals(item.getName())
        );

        if(items.isEmpty()){
            return repository.add(item);
        }
        return false;
    }
}
