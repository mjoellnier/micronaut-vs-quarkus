package org.acme.quickstart.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.quickstart.model.Animal;
import org.acme.quickstart.service.AnimalService;

@Path("/animals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnimalController {

    @Inject
    AnimalService animalService;

    @GET
    public List<Animal> list() {
        return animalService.list();
    }

    @POST
    public List<Animal> add(Animal animal) {
        animalService.add(animal);
        return list();
    }

}