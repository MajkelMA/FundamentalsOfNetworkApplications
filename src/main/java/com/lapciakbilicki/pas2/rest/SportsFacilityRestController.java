package com.lapciakbilicki.pas2.rest;

import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import com.lapciakbilicki.pas2.repository.Repository;
import com.lapciakbilicki.pas2.service.SportsFacilityService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @GET
    @Path("/filter/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SportsFacility> getFilteredByName(@PathParam("name") String name){
        return this.sportsFacilityService.getByCondition(sportsFacility -> sportsFacility.getName().contains(name));
    }

    @DELETE
    @Path("{id}")
    public Response deleteFacility(@PathParam("id") String id){
        boolean flag = this.sportsFacilityService.delete(id);
        if(flag)
            return Response.status(200).entity("Success").build();
        else
            return Response.status(404).entity("Facility does not exist").build();
    }
}
