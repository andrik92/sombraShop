package com.andriyprokip.sombrashop.manager.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.andriyprokip.sombrashop.dao.CityDao;
import com.andriyprokip.sombrashop.entity.City;
import com.andriyprokip.sombrashop.entity.Lot;
import com.andriyprokip.sombrashop.manager.interfaces.ICityManager;

@Service
public class CityManagerImplementation implements ICityManager {

	@Autowired
	private CityDao cityDao;

	@Override
	@Transactional
	public void save(City city) {
		cityDao.save(city);
	}

	@Override
	@Transactional
	public void delete(City city) {
		cityDao.delete(city);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<City> findAllCity() {
		return cityDao.findAll();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public City findByIdCity(Integer id) {
		return cityDao.findOne(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public City findByCityName(String name) {
		return cityDao.findByNameCity(name);
	}

	@Override
	public List<String> citiesOfLots(List<Lot> lots) {
		Set<String> citiesSet = new HashSet<String>();
		for (Lot lot : lots) {
			if (lot.getCity() != null) {
				citiesSet.add(lot.getCity().getCityName());
			}
		}
		List<String> cities = new ArrayList<>();
		cities.addAll(citiesSet);
		Collections.sort(cities);
		cities.add(0, "All cities");
		return cities;
	}

	@Override
	@Transactional
	public void deactivateById(int id) {
		cityDao.deactivateById(id);

	}

	@Override
	@Transactional
	public void activateById(int id) {
		cityDao.activateById(id);

	}

}
