package com.redhat.demo.farm.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

	private Map<Long, Animal> farm = new ConcurrentHashMap<Long, Animal>();

	public AnimalService() {

	}

	public Animal getById(Long id) {
		LOG.info("getting animal by id: " + id);
		return (farm.containsKey(id)) ? farm.get(id) : null;
	}

	public List<Animal> getAll() {
		LOG.info("getting all animals");
		return new ArrayList<Animal>(farm.values());
	}

	public Animal update(Long id, Animal animal) {
		LOG.info("updating animal with id: " + id + " to " + animal);
		farm.put(id, animal);
		return animal;
	}

	public Animal create(Animal animal) {
		LOG.info("adding animal: " + animal);
		farm.put(animal.getId(), animal);
		return animal;
	}

	public void delete(Animal animal) {
		LOG.info("deleting animal: " + animal);
		if (farm.containsKey(animal.getId()))
			farm.remove(animal.getId());
	}

	public Boolean feed(Animal animal) throws InterruptedException {

		LOG.info("feeding animal: " + animal);

		if (farm.containsKey(animal.getId())) {
			Animal a = farm.get(animal.getId());
			a.setTimesFed(a.getTimesFed() + 1);
			Thread.sleep(2000L);

			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public Boolean harvest(Animal animal) throws InterruptedException {
		LOG.info("harvesting animal: " + animal);
		if (farm.containsKey(animal.getId())) {
			Animal a = farm.get(animal.getId());
			a.setHarvested(Boolean.TRUE);
			Thread.sleep(5000L);
			return a.getHarvested();
		} else {
			return Boolean.FALSE;
		}

	}

}
