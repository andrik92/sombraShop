package com.andriyprokip.sombrashop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andriyprokip.sombrashop.entity.Location;

@Repository
public interface LocationDao extends JpaRepository<Location, Integer>{
	
	public Location findLocationsInCity(@Param("name") String cityName);

}
