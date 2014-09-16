package com.andriyprokip.sombrashop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LOCATION")
@NamedQueries({
		@NamedQuery(name = "findAllLocation", query = "from Location"),
		@NamedQuery(name = "Location.findLocationsInCity", query = "SELECT l FROM Location l JOIN l.city c WHERE c.cityName = :name") })
public class Location extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOCATION_ID")
	private int locationId;

	@ManyToOne
	@JoinColumn(name = "CITY_ID")
	private City city;

	@Column(name = "STREET")
	private String street;

	@Column(name = "BUILDING")
	private Integer building;

	@Column(name = "APARTMENT")
	private Integer apartment;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "location")
	private User user;

	public Location() {
	}

	public Location(String street, Integer build, Integer aprt) {
		this.street = street;
		this.building = build;
		this.apartment = aprt;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getBuilding() {
		return building;
	}

	public void setBuilding(Integer building) {
		this.building = building;
	}

	public Integer getApartment() {
		return apartment;
	}

	public void setApartment(Integer apartment) {
		this.apartment = apartment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
