package com.lapciakbilicki.pas2.validator;

import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import com.lapciakbilicki.pas2.repository.SportsFacilityRepository;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueImpl implements ConstraintValidator<Unique, SportsFacility> {

    @Inject
    SportsFacilityRepository sportsFacilityRepository;

    @Override
    public boolean isValid(SportsFacility s, ConstraintValidatorContext constraintValidatorContext) {
        SportsFacility sportsFacility = sportsFacilityRepository.getAll().stream()
                .filter(item -> item.getName().equals(s.getName()))
                .findFirst()
                .orElse(null);


        if(sportsFacility != null && s.getId().equals(sportsFacility.getId()))
            return true;
        return sportsFacility == null;
    }
}
