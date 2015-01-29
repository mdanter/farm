package com.redhat.demo.farm.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Animal implements Serializable {
	
	@XmlTransient
	private static final long serialVersionUID = 7488883117216954458L;

	@XmlAttribute
	private Long id;
	
	@XmlElement
	private String species;
	
	@XmlElement
	private String name;
	
	@XmlElement
	private String imageSrc;
	
	@XmlElement
	private Long timesFed = 0L;
	
	@XmlElement
	private Boolean harvested = Boolean.FALSE;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public Long getTimesFed() {
		return timesFed;
	}

	public void setTimesFed(Long timesFed) {
		this.timesFed = timesFed;
	}

	public Boolean getHarvested() {
		return harvested;
	}

	public void setHarvested(Boolean harvested) {
		this.harvested = harvested;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((harvested == null) ? 0 : harvested.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((imageSrc == null) ? 0 : imageSrc.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((species == null) ? 0 : species.hashCode());
		result = prime * result
				+ ((timesFed == null) ? 0 : timesFed.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (harvested == null) {
			if (other.harvested != null)
				return false;
		} else if (!harvested.equals(other.harvested))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imageSrc == null) {
			if (other.imageSrc != null)
				return false;
		} else if (!imageSrc.equals(other.imageSrc))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (species == null) {
			if (other.species != null)
				return false;
		} else if (!species.equals(other.species))
			return false;
		if (timesFed == null) {
			if (other.timesFed != null)
				return false;
		} else if (!timesFed.equals(other.timesFed))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", species=" + species + ", name=" + name
				+ ", imageSrc=" + imageSrc + ", timesFed=" + timesFed
				+ ", harvested=" + harvested + "]";
	}

	
	
	

}
