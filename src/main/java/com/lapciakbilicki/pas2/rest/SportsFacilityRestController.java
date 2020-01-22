package com.lapciakbilicki.pas2.rest;

import com.lapciakbilicki.pas2.model.sportsfacility.BasketballFacility;
import com.lapciakbilicki.pas2.model.sportsfacility.FootballFacility;
import com.lapciakbilicki.pas2.model.sportsfacility.SportsFacility;
import com.lapciakbilicki.pas2.service.SportsFacilityService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;


@Path("/facilities")
public class SportsFacilityRestController {

    @Inject
    SportsFacilityService sportsFacilityService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SportsFacility> getAll() {
        return sportsFacilityService.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SportsFacility get(@NotNull @PathParam("id") String id) {
        return sportsFacilityService.get(id);
    }

    @GET
    @Path("/filter/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SportsFacility> getFilteredByName(@NotNull @PathParam("name") String name) {
        return this.sportsFacilityService.getByCondition(sportsFacility -> sportsFacility.getName().contains(name));
    }

    @DELETE
    @Path("{id}")
    public Response deleteFacility(@NotNull @PathParam("id") String id) {
        boolean flag = this.sportsFacilityService.delete(id);
        if (flag)
            return Response.status(200).entity("Success").build();
        else
            return Response.status(404).entity("Facility does not exist").build();
    }

    @PUT
    @Path("/basketball/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFacility(@Valid BasketballFacility basketballFacility) {
        if (this.sportsFacilityService.update(basketballFacility)) {
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @PUT
    @Path("/football/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFacility(@Valid FootballFacility footballFacility) {
        if (this.sportsFacilityService.update(footballFacility)) {
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    @POST
    @Path("/football")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFacility(@Valid FootballFacility footballFacility) {
        footballFacility.setId(UUID.randomUUID().toString());
        boolean result = this.sportsFacilityService.add(footballFacility);
        if (result)
            return Response.status(200).build();
        else
            return Response.status(400).build();
    }

    @POST
    @Path("/basketball")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFacility(@Valid BasketballFacility basketballFacility) {
        basketballFacility.setId(UUID.randomUUID().toString());
        boolean result = this.sportsFacilityService.add(basketballFacility);
        if (result)
            return Response.status(200).build();
        else
            return Response.status(400).build();
    }
}
