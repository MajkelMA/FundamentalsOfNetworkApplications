package com.lapciakbilicki.pas2.controller;

import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import com.lapciakbilicki.pas2.service.SportsFacilityService;

import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@RequestScoped
@Named
public class SportsFacilityController implements Serializable {
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

    public void deleteSportsFacility(String id){
        sportsFacilityService.remove(sportsFacilityService.get(id));
    }
}
