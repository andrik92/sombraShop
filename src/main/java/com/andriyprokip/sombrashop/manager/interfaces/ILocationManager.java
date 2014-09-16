package com.andriyprokip.sombrashop.manager.interfaces;

import com.andriyprokip.sombrashop.entity.Location;

public interface ILocationManager {

	public Location findByCity(String cityName);

	public void save(Location newLocation);
	

}
