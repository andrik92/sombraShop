package com.andriyprokip.sombrashop.manager.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.andriyprokip.sombrashop.dao.LocationDao;
import com.andriyprokip.sombrashop.entity.Location;
import com.andriyprokip.sombrashop.manager.interfaces.ILocationManager;

@Service
public class LocationManager  implements ILocationManager {

	@Autowired
	private	LocationDao locationDao;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Location findByCity(String cityName) {
		return locationDao.findLocationsInCity(cityName);
	}

	@Override
	public void save(Location newLocation) {
		locationDao.save(newLocation);
	} 

}
