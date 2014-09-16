package com.andriyprokip.sombrashop.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@NamedQueries({
		@NamedQuery(name = "City.findAllCityes", query = "from City"),
		@NamedQuery(name = "City.findByIdCity", query = "from City where id = :a"),
		@NamedQuery (name="City.deactivateById", query= "update City as u set u.enabledCity =0  where u.cityId = ?1"),
		@NamedQuery (name="City.activateById", query= "update City as u set u.enabledCity =1  where u.cityId = ?1"),
		@NamedQuery(name = "City.findByNameCity", query = "from City where cityName = :a") ,
		@NamedQuery(name = "City.findAllEnabledCityes", query = "from City where enabledCity = true")
		})

@Table(name = "CITY")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CITY_ID")
	private Integer cityId;
	
	@Column(name = "CITY_NAME")
	private String cityName;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "city")
	@JsonManagedReference
	Set<Location> locations = new HashSet<>();

	private boolean enabledCity;

	public City() {
	}

	public City(String cityName) {
		this.cityName = cityName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Set<Location> getLocations() {
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	public boolean getEnabledCity() {
		return enabledCity;
	}

	public void setEnabledCity(boolean enabledCity) {
		this.enabledCity = enabledCity;
	}

}
