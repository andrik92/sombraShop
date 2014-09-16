package com.andriyprokip.sombrashop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andriyprokip.sombrashop.entity.City;

@Repository
public interface CityDao extends JpaRepository<City, Integer> {

	public City findByNameCity(@Param("a")String name);
	
	@Modifying
	public void deactivateById(int id);
	
	@Modifying
	public void activateById(int id);
}
