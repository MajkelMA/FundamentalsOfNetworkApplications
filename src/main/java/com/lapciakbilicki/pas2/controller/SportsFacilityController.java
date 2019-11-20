package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import com.lapciakbilicki.pas2.service.SportsFacilityService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
public class SportsFacilityController implements Serializable {
    @Inject
    private SportsFacilityService sportsFacilityService;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    private List<SportsFacility> sportsFacilities;

    @PostConstruct
    public void loadSportsFacilities() {
        sportsFacilities = new ArrayList<>(sportsFacilityService.getAll());
    }

    public List<SportsFacility> getAll() {
        return sportsFacilities;
    }

    public SportsFacility getActiveSportsFacility() {
        return this.sportsFacilityService.get(requestMap.get("id"));
    }

    public void deleteSportsFacility(String id) {
        SportsFacility facilityToRemove = sportsFacilities
                .stream()
                .filter(sportsFacility -> sportsFacility
                        .getId()
                        .equals(id))
                .findFirst()
                .orElse(null);
        sportsFacilityService.remove(facilityToRemove);
        loadSportsFacilities();
    }
}
