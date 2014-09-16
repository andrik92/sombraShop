package com.andriyprokip.sombrashop.manager.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.andriyprokip.sombrashop.dao.CityDao;
import com.andriyprokip.sombrashop.dao.LocationDao;
import com.andriyprokip.sombrashop.dao.RoleDao;
import com.andriyprokip.sombrashop.dao.UserDao;
import com.andriyprokip.sombrashop.entity.Location;
import com.andriyprokip.sombrashop.entity.Role;
import com.andriyprokip.sombrashop.entity.User;
import com.andriyprokip.sombrashop.manager.interfaces.IUserManager;

@Service
public class UserManager implements IUserManager {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private CityDao cityDao;

	@Autowired
	private LocationDao locationDao;

	@Override
	@Transactional
	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		List<Role> roles = new ArrayList<>();
		roles.add(roleDao.findByName("ROLE_USER"));
		user.setRoles(roles);
		Location location = new Location();
		location.setCity(null);
		user.setLocation(location);
		locationDao.save(location);
		userDao.save(user);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<User> findAll(int pageNumber, int pageSize) {
		return userDao.findAll(new PageRequest(pageNumber, pageSize));
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public User findById(Integer id) {
		return userDao.findOne(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public User findByLogin(String login) {
		return userDao.findByLogin(login);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<User> findAllByFirstName(String firstName) {
		String firstNames = firstName + "%";
		return userDao.findAllByFirstName(firstNames);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<User> findAllByLastName(String lastName) {
		String lastNames = lastName + "%";
		return userDao.findAllByLastName(lastNames);
	}

	@Override
	@Transactional
	public void deleteByLogin(String login) {
		userDao.deleteByLogin(login);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		userDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public User findByPhoneNumber(String phoneNumber) {
		return userDao.findByPhoneNumber(phoneNumber);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	@Transactional
	public void deactivateById(Integer id) {
		userDao.deactivateById(id);

	}

	@Override
	@Transactional
	public void activateById(Integer id) {
		userDao.activateById(id);

	}

	@Override
	@Transactional
	public void update(User user) {
		userDao.saveAndFlush(user);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	public List<User> findUsersByFirstName(String first) {
		return userDao.findUsersByFirstName(first);

	}

	public List<User> findUsersByLogin(String first) {
		return userDao.findUsersByLogin(first);

	}

	public List<User> findUsersByLastName(String first) {
		return userDao.findUsersByLastName(first);

	}
}
