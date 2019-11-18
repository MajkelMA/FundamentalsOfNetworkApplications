package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import com.lapciakbilicki.pas2.service.SportsFacilityService;

import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@RequestScoped
@Named
public class SportsFacilityController {
    @Inject
    private SportsFacilityService sportsFacilityService;


    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    public List<SportsFacility> getAll(){
        return this.sportsFacilityService.getAll();
    }

    public SportsFacility getActiveSportsFacility(){
        return this.sportsFacilityService.get(requestMap.get("id"));
    }
}
