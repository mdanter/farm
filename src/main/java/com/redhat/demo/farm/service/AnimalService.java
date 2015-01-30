package com.redhat.demo.farm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.demo.farm.model.Animal;

@Named
@ApplicationScoped
public class AnimalService {

	private static final Logger LOG = LoggerFactory.getLogger(AnimalService.class);

	private Map<String, Animal> farm = new ConcurrentHashMap<String, Animal>();

	public AnimalService() {
		Animal a1 = new Animal();
		a1.setName("Larry");
		a1.setSold(false);
		a1.setSpecies("Lama");
		
		Animal a2 = new Animal();
		a2.setName("Harold");
		a2.setSold(false);
		a2.setSpecies("Sheep");
		
		create(a1);
		create(a2);
		
	}

	public Animal getById(Long id) {
		LOG.info("getting animal by id: " + id);
		return (farm.containsKey(id)) ? farm.get(id) : null;
	}

	public List<Animal> getAll() {
		LOG.info("getting all animals");
		return new ArrayList<Animal>(farm.values());
	}

	public Animal update(String id, Animal animal) {
		LOG.info("updating animal with id: " + id + " to " + animal);
		farm.put(id, animal);
		return animal;
	}

	public Animal create(Animal animal) {
		
		String uuid = UUID.randomUUID().toString();
		
		animal.setImageSrc("http://lorempixel.com/350/350/animals/"
				+ animal.getName());
		animal.setId(uuid);

		LOG.info("adding animal: " + animal);
		
		farm.put(uuid, animal);
		return animal;
	}

	public void delete(String id) {
		LOG.info("deleting animal: " + id);
		if (farm.containsKey(id))
			farm.remove(id);
	}

	public Boolean feed(Animal animal) throws InterruptedException {

		LOG.info("feeding animal: " + animal);

		if (farm.containsKey(animal.getId())) {
			Animal a = farm.get(animal.getId());
			a.setTimesFed(a.getTimesFed() + 1);
			Thread.sleep(5000L);

			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public Boolean sell(Animal animal) throws InterruptedException {
		LOG.info("sellinging animal: " + animal);
		if (farm.containsKey(animal.getId())) {
			Animal a = farm.get(animal.getId());
			a.setSold(Boolean.TRUE);
			Thread.sleep(2000L);
			return a.getSold();
		} else {
			return Boolean.FALSE;
		}

	}

}
