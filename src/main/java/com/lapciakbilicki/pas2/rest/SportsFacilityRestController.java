package com.lapciakbilicki.pas2.rest;

import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import com.lapciakbilicki.pas2.service.Service;
import com.lapciakbilicki.pas2.service.SportsFacilityService;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/facilities")
public class SportsFacilityRestController {

    @Inject
    SportsFacilityService sportsFacilityService = new SportsFacilityService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SportsFacility> getAll(){
        return sportsFacilityService.getAll();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SportsFacility get(@PathParam("id") String id){
        return sportsFacilityService.get(id);
    }
}
