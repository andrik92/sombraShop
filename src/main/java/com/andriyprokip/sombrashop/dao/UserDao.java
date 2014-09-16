package com.andriyprokip.sombrashop.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;


import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andriyprokip.sombrashop.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	public List<User> findAllByFirstName(@Param(value = "firstName")String firstName);

	public List<User> findAllByLastName(@Param(value = "lastName")String lastName);

	public User findByLogin(@Param("login")String login);

	public void deleteByLogin(String login);
	
	public User findByPhoneNumber(@Param(value = "phoneNumber") String phoneNumber);

	public void deleteById(Integer id);

	@Modifying
	public void deactivateById(Integer id);
	
	@Modifying
	public void activateById(Integer id);

	public User findByEmail(@Param(value = "email")String email);
		
	@Override
	public Page<User> findAll( Pageable pageable);
	
	public List<User> findUsersByFirstName(@Param(value = "firstName") String firstName); 
	
	public List<User> findUsersByLastName(@Param(value = "lastName") String lastName); 
	
	public List<User> findUsersByLogin(@Param(value = "login") String login);
	
}
