package com.andriyprokip.sombrashop.manager.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.andriyprokip.sombrashop.entity.User;

public interface IUserManager {

	public void save(User user);

	public User findById(Integer id);

	public User findByLogin(String login);

	public List<User> findAllByFirstName(String firstName);

	public List<User> findAllByLastName(String lastName);

	public void deleteByLogin(String login);

	public void deleteById(Integer id);

	public void deactivateById(Integer id);
	
	public void activateById(Integer id);

	public User findByPhoneNumber(String phoneNumber);

	public User findByEmail(String email);
	
	public void update(User user);
	
	public Page<User> findAll(int pageNumber,int pageSize);

	public List<User> findAll();
	
	public List <User> findUsersByLogin(String first);
	
	public List <User> findUsersByFirstName(String first);
	
	public List <User> findUsersByLastName(String first);
}
