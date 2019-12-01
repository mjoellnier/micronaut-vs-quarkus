package org.acme.quickstart.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.quickstart.model.Plant;

@Path("/plants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlantController {

    @GET
    public List<Plant> list() {
        return Plant.listAll();
    }

    @POST
    public Response create(Plant plant) {
        plant.persist();
        return Response.ok().build();
    }

    @GET
    @Path("/count")
    public long count() {
        return Plant.count();
    }

}