package com.andriyprokip.sombrashop.manager.interfaces;

import java.util.List;

import com.andriyprokip.sombrashop.entity.City;
import com.andriyprokip.sombrashop.entity.Lot;

public interface ICityManager {

	public void save(City city);

	public void delete(City city);

	public List<City> findAllCity();

	public City findByIdCity(Integer id);

	public City findByCityName(String name);

	public List<String> citiesOfLots(List<Lot> lots);

	public void deactivateById(int id);

	public void activateById(int id);
}
