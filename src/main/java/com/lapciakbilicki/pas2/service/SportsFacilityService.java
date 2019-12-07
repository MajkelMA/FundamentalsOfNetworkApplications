package com.lapciakbilicki.pas2.service;

import com.lapciakbilicki.pas2.model.sportsfacility.BasketballFacility;
import com.lapciakbilicki.pas2.model.sportsfacility.Field;
import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import com.lapciakbilicki.pas2.repository.SportsFacilityRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestScoped
public class SportsFacilityService extends ServiceAdapter<SportsFacility> {

    public SportsFacilityService() {

    }

    @PostConstruct
    @Inject
    public void init(SportsFacilityRepository sportsFacilityRepository) {
        repository = sportsFacilityRepository;
    }

    @Override
    public boolean add(SportsFacility item) {
        List<SportsFacility> items = repository.getByCondition(
                sportsFacility -> sportsFacility.getName().equals(item.getName())
        );

        if (items.isEmpty()) {
            return repository.add(item);
        }
        return false;
    }

    public void changeSportsFacilityAccess(String id) {
        List<SportsFacility> sportsFacilities = this.repository.getByCondition(sportsFacility -> sportsFacility.getId().equals(id));
        if (!sportsFacilities.isEmpty()) {
            sportsFacilities.get(0).setAccess(!sportsFacilities.get(0).isAccess());
        }
    }

    public void deleteSportsFacility(String id) {
        SportsFacility facilityToRemove = this.repository.getAll()
                .stream()
                .filter(sportsFacility -> sportsFacility
                        .getId()
                        .equals(id))
                .findFirst()
                .orElse(null);
        if (facilityToRemove != null)
            this.repository.remove(facilityToRemove);
    }

    public List<SportsFacility> filterFacility(String priceFromFacilityFilter, String priceToFacilityFilter, String nameFacilityFilter) {
        if (Integer.parseInt(priceToFacilityFilter) == 0 && Integer.parseInt(priceFromFacilityFilter) == 0) {
            return this.repository.getAll()
                    .stream()
                    .filter(fac -> fac.getName().contains(nameFacilityFilter))
                    .collect(Collectors.toList());
        } else {
            return this.repository.getAll()
                    .stream()
                    .filter(fac -> fac.getPricePerHours() >= Integer.parseInt(priceFromFacilityFilter))
                    .filter(fac -> fac.getPricePerHours() <= Integer.parseInt(priceToFacilityFilter))
                    .filter(fac -> fac.getName().contains(nameFacilityFilter))
                    .collect(Collectors.toList());
        }
    }
}
