package com.redhat.demo.farm.rest;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.redhat.demo.farm.model.Animal;
import com.redhat.demo.farm.service.AnimalService;

@Path("/v1/animal")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnimalEndpoint {

	@Inject
	private AnimalService animalService;

	@GET
	@Path("/test")
	public Animal test() {
		Animal a = new Animal();
		a.setSold(true);
		a.setId(UUID.randomUUID().toString());
		a.setName("Lima Bean");
		a.setImageSrc("http://lorempixel.com/350/350/animals/" + a.getName());
		a.setSpecies("lemur");
		a.setTimesFed(0L);
		return a;
	}

	@GET
	@Path("/{id}")
	public Animal getById(@PathParam("id") Long id) {
		return animalService.getById(id);
	}

	@GET
	public List<Animal> getAll() {
		return animalService.getAll();
	}

	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") String id, Animal animal) {
		animalService.update(id, animal);
		return ResponseUtil.success("Updated "+animal.getName()+"!");
	}

	@POST
	public Response create(Animal animal) {
		animalService.create(animal);
		return ResponseUtil.success("Created "+animal.getName()+"!");
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") String id) {
		animalService.delete(id);
		return ResponseUtil.success("Deleted animal with ID: "+id+"!");
	}

	@PUT
	@Path("/feed/{id}")
	public Response feed(@PathParam("id") String id, Animal animal)
			throws InterruptedException {
		animalService.feed(animal);
		return ResponseUtil.success("Fed "+animal.getName()+" the "+animal.getSpecies()+"!");
	}

	@PUT
	@Path("/sell/{id}")
	public Response sell(@PathParam("id") String id, Animal animal)
			throws InterruptedException {
		animalService.sell(animal);
		return ResponseUtil.success("Sold "+animal.getName()+" the "+animal.getSpecies()+"!");
	}
	
	@GET
	@Path("/nono")
	public Response nono(){
		return ResponseUtil.unauthorized();
	}
}
