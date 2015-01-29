package com.redhat.demo.farm.rest;

import java.util.List;
import java.util.Random;

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

import com.redhat.demo.farm.dto.AnimalService;
import com.redhat.demo.farm.model.Animal;

@Path("/v1/animal")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnimalEndpoint {

	@Inject
	private AnimalService animalService;
	
	@GET
	@Path("/test")
	public Animal test(){
		
		Long id = new Random().nextLong() * ((12345 - 10) + 1) + 10;
		
		Animal a = new Animal();
		a.setHarvested(true);
		a.setId(id);
		a.setName("Lima Bean");
		a.setImageSrc("http://placehold.it/350x150&text="+a.getName());
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
	public List<Animal> getAll(@PathParam("id") Long id) {
		return animalService.getAll();
	}

	@PUT
	@Path("/{id}")
	public Animal update(@PathParam("id") Long id, Animal animal) {
		return animalService.update(id, animal);
	}

	@POST
	public Animal create(Animal animal) {
		return animalService.create(animal);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id, Animal animal) {
		animalService.delete(animal);
	}

	@PUT
	@Path("/feed/{id}")
	public Boolean feed(@PathParam("id") Long id, Animal animal) throws InterruptedException {
		return animalService.feed(animal);
	}

	@PUT
	@Path("/harvest/{id}")
	public Boolean harvest(@PathParam("id") Long id, Animal animal) throws InterruptedException {
		return animalService.harvest(animal);
	}
}
