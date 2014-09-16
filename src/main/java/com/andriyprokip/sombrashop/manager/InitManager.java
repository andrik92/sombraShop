package com.andriyprokip.sombrashop.manager;

/**
 * Filling the database by first time
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andriyprokip.sombrashop.dao.CartDao;
import com.andriyprokip.sombrashop.dao.CategoryDao;
import com.andriyprokip.sombrashop.dao.CityDao;
import com.andriyprokip.sombrashop.dao.LocationDao;
import com.andriyprokip.sombrashop.dao.LotDao;
import com.andriyprokip.sombrashop.dao.RoleDao;
import com.andriyprokip.sombrashop.dao.UserDao;
import com.andriyprokip.sombrashop.entity.Category;
import com.andriyprokip.sombrashop.entity.City;
import com.andriyprokip.sombrashop.entity.Location;
import com.andriyprokip.sombrashop.entity.Lot;
import com.andriyprokip.sombrashop.entity.LotStatus;
import com.andriyprokip.sombrashop.entity.Picture;
import com.andriyprokip.sombrashop.entity.Role;
import com.andriyprokip.sombrashop.entity.User;
import com.andriyprokip.sombrashop.manager.interfaces.ILotManager;

@Service
@Transactional
public class InitManager {
	// Category
	final static int N1 = 0;
	// City
	final static int N2 = 1;
	// Location
	final static int N3 = 9;
	// User
	final static int N4 = 2;
	// Lot
	final static int N5 = 2;

	@Autowired
	private CartDao cartDao;

	private BCryptPasswordEncoder encoder;

	@Autowired
	private ILotManager lotManager;

	@Autowired
	private LotDao lotDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private CityDao cityDao;
	@Autowired
	private LocationDao locationDao;

	@Autowired
	private RoleDao roleDao;

	private Category motors;
	private Category motorsCars;
	private Category motorsCycles;
	private Category electronics;
	private Category sport;
	private Category books;
	private Category fashion;
	private Category collection;
	private Category homeGarden;
	private Category other;
	private Category electronicsSmartphones;
	private Category electronicsCameras;
	private Category electronicsPhones;

	private City lviv;
	private City kyiv;
	private City ternopil;
	private City defaultCity;

	private Location lvivLocation;
	private Location kyivLocation;
	private Location ternopilLocation;
	private Location defaultLocation;

	private User superman;
	private User ironman;
	private User userAdmin;

	private Role adminRole;
	private Role userRole;

	private Lot lot1;
	private Lot lot2;
	private Lot lot3;
	private Lot lot4;
	private Lot lot5;
	private Lot lot6;
	private Lot lot7;
	private Lot lot8;
	private Lot lot9;
	private Lot lot10;
	private Lot lot11;
	private Lot lot12;
	private Lot lot13;
	private Lot lot14;
	private Lot lot15;
	private Lot lot16;
	private Lot lot17;
	private Lot lot18;
	private Lot lot19;
	private Lot lot20;
	private Lot lot21;
	private Lot lot22;
	private Lot lot23;
	private Lot lot24;
	private Lot lot25;
	private Lot lot26;
	private Lot lot27;
	private Lot lot28;
	private Lot lot29;
	private Lot lot30;
	private Lot lot31;
	private Lot lot32;
	private Lot lot33;
	private Lot lot34;

	@PostConstruct
	public void init() {
		cityDao.save(cities());
		locationDao.save(locations());
		roleDao.save(roles());
		userDao.save(users());
		categoryDao.save(categories());
		List<Lot> lots = lots();
		lots = pictures(lots);
		lotManager.save(lots);
	}

	private List<Role> roles() {

		List<Role> roles = new ArrayList<Role>();

		adminRole = new Role();
		userRole = new Role();
		adminRole.setName("ROLE_ADMIN");
		userRole.setName("ROLE_USER");
		roles.add(adminRole);
		roles.add(userRole);
		return roles;
	}

	private List<User> users() {

		encoder = new BCryptPasswordEncoder();
		List<User> users = new ArrayList<User>();
		List<Role> roles = new ArrayList<Role>();
		List<Role> adminRoles = new ArrayList<Role>();

		roles.add(userRole);
		superman = new User();
		superman.setFirstName("Klark");
		superman.setLastName("Kent");
		superman.setLogin("superman");
		superman.setPassword(encoder.encode("superman"));
		superman.setEmail("super@mail.com");
		superman.setPhoneNumber("(032)9113235");
		superman.setEnabled(true);
		superman.setRoles(roles);
		superman.setLocation(kyivLocation);
		users.add(superman);

		ironman = new User();
		ironman.setFirstName("Johny");
		ironman.setLastName("Stark");
		ironman.setLogin("ironman");
		ironman.setPassword(encoder.encode("ironman"));
		ironman.setEmail("ironman@mail.com");
		ironman.setPhoneNumber("(032)1020203");
		ironman.setEnabled(true);
		ironman.setRoles(roles);
		ironman.setLocation(ternopilLocation);
		users.add(ironman);

		adminRoles.add(adminRole);
		adminRoles.add(userRole);
		userAdmin = new User();
		userAdmin.setLogin("admin");
		userAdmin.setPassword(encoder.encode("admin"));
		userAdmin.setFirstName("Lesuyk");
		userAdmin.setLastName("Jkeeeeeeeeeee");
		userAdmin.setEnabled(true);
		userAdmin.setPhoneNumber("0638201232");
		userAdmin.setEmail("mail@mail.com");
		userAdmin.setRoles(adminRoles);
		userAdmin.setLocation(lvivLocation);
		users.add(userAdmin);

		return users;
	}

	private List<Category> categories() {

		List<Category> categories = new ArrayList<Category>();

		motors = new Category();
		motors.setCategoryName("Motors");
		motors.setCategoryNameUA("Авто & мото");
		categories.add(motors);
		motorsCars = new Category();
		motorsCars.setCategoryName("Cars");
		motorsCars.setCategoryNameUA("Легкові автомобілі");
		motorsCars.setParent(motors);
		categories.add(motorsCars);
		motorsCycles = new Category();
		motorsCycles.setCategoryName("Cycles");
		motorsCycles.setCategoryNameUA("Велосипеди");
		motorsCycles.setParent(motors);
		categories.add(motorsCycles);
		electronics = new Category();
		electronics.setCategoryName("Electronics");
		electronics.setCategoryNameUA("Електроніка");
		categories.add(electronics);
		sport = new Category();
		sport.setCategoryName("Sport goods");
		sport.setCategoryNameUA("Спортивні товари");
		categories.add(sport);
		books = new Category();
		books.setCategoryName("Books");
		books.setCategoryNameUA("Книги");
		categories.add(books);
		fashion = new Category();
		fashion.setCategoryName("Fashion");
		fashion.setCategoryNameUA("Мода");
		categories.add(fashion);
		collection = new Category();
		collection.setCategoryName("Collection");
		collection.setCategoryNameUA("Колекції");
		categories.add(collection);
		homeGarden = new Category();
		homeGarden.setCategoryName("Home & Garden");
		homeGarden.setCategoryNameUA("Все для дому");
		categories.add(homeGarden);
		other = new Category();
		other.setCategoryName("Other");
		other.setCategoryNameUA("Інші");
		categories.add(other);

		electronicsPhones = new Category();
		electronicsPhones.setCategoryName("Phones");
		electronicsPhones.setCategoryNameUA("Телефони");
		electronicsPhones.setParent(electronics);
		categories.add(electronicsPhones);

		electronicsSmartphones = new Category();
		electronicsSmartphones.setCategoryName("Smartphones");
		electronicsSmartphones.setCategoryNameUA("Смартфони ");
		electronicsSmartphones.setParent(electronicsPhones);
		categories.add(electronicsSmartphones);

		electronicsCameras = new Category();
		electronicsCameras.setCategoryName("Cameras");
		electronicsCameras.setCategoryNameUA("Фото і відео");
		electronicsCameras.setParent(electronics);
		categories.add(electronicsCameras);

		return categories;
	}

	private List<Lot> lots() {

		List<Lot> lots = new ArrayList<Lot>();

		lot1 = new Lot();
		lot1.setLotName("iPhone 3g");
		lot1.setCategory(electronicsSmartphones);
		lot1.setSeller(userAdmin);
		lot1.setPrice(20.0);
		lot1.setCity(lviv);
		lots.add(lot1);

		lot2 = new Lot();
		lot2.setLotName("iPhone 3gs");
		lot2.setCategory(electronicsSmartphones);
		lot2.setSeller(userAdmin);
		lot2.setPrice(15.0);
		lot2.setCity(kyiv);
		lots.add(lot2);

		lot3 = new Lot();
		lot3.setLotName("iPhone 4g");
		lot3.setCategory(electronicsSmartphones);
		lot3.setSeller(userAdmin);
		lot3.setPrice(100.0);
		lot3.setCity(ternopil);
		lots.add(lot3);

		lot4 = new Lot();
		lot4.setLotName("iPhone 4gs");
		lot4.setCategory(electronicsSmartphones);
		lot4.setSeller(userAdmin);
		lot4.setPrice(150.0);
		lot4.setCity(lviv);
		lots.add(lot4);

		lot5 = new Lot();
		lot5.setLotName("iPad 3g");
		lot5.setCategory(electronicsSmartphones);
		lot5.setSeller(userAdmin);
		lot5.setPrice(50.0);
		lot5.setCity(ternopil);
		lots.add(lot5);

		lot6 = new Lot();
		lot6.setLotName("Canon EOS 450d");
		lot6.setCategory(electronicsCameras);
		lot6.setSeller(userAdmin);
		lot6.setPrice(300.0);
		lot6.setCity(kyiv);
		lots.add(lot6);

		lot7 = new Lot();
		lot7.setLotName("Nikon D3100");
		lot7.setCategory(electronicsCameras);
		lot7.setSeller(userAdmin);
		lot7.setPrice(230.0);
		lot7.setCity(lviv);
		lots.add(lot7);

		lot8 = new Lot();
		lot8.setLotName("Nikon D5300");
		lot8.setCategory(electronicsCameras);
		lot8.setSeller(userAdmin);
		lot8.setPrice(1.0);
		lot8.setLotStatus(LotStatus.AVAILABLE);
		lot8.setCity(lviv);
		lots.add(lot8);

		lot9 = new Lot();
		lot9.setLotName("HTC One M8");
		lot9.setCategory(electronicsSmartphones);
		lot9.setSeller(userAdmin);
		lot9.setPrice(1.0);
		lot9.setLotStatus(LotStatus.AVAILABLE);
		lot9.setCity(lviv);
		lots.add(lot9);

		lot10 = new Lot();
		lot10.setLotName("HTC Desire 601");
		lot10.setCategory(electronicsSmartphones);
		lot10.setSeller(userAdmin);
		lot10.setPrice(1.0);
		lot10.setLotStatus(LotStatus.AVAILABLE);
		lot10.setCity(lviv);
		lots.add(lot10);

		lot11 = new Lot();
		lot11.setLotName("Caterpillar B15");
		lot11.setCategory(electronicsSmartphones);
		lot11.setSeller(userAdmin);
		lot11.setPrice(1.0);
		lot11.setLotStatus(LotStatus.AVAILABLE);
		lot11.setCity(lviv);
		lots.add(lot11);

		lot12 = new Lot();
		lot12.setLotName("Lenovo A516");
		lot12.setCategory(electronicsSmartphones);
		lot12.setSeller(userAdmin);
		lot12.setPrice(1.0);
		lot12.setLotStatus(LotStatus.AVAILABLE);
		lot12.setCity(lviv);
		lots.add(lot12);

		lot13 = new Lot();
		lot13.setLotName("Lenovo A680");
		lot13.setCategory(electronicsSmartphones);
		lot13.setSeller(userAdmin);
		lot13.setPrice(1.0);
		lot13.setLotStatus(LotStatus.AVAILABLE);
		lot13.setCity(lviv);
		lots.add(lot13);

		lot14 = new Lot();
		lot14.setLotName("Sony Xperia X1");
		lot14.setCategory(electronicsSmartphones);
		lot14.setSeller(userAdmin);
		lot14.setPrice(1.0);
		lot14.setLotStatus(LotStatus.AVAILABLE);
		lot14.setCity(lviv);
		lots.add(lot14);

		lot15 = new Lot();
		lot15.setLotName("Sony M Dual");
		lot15.setCategory(electronicsSmartphones);
		lot15.setSeller(userAdmin);
		lot15.setPrice(1.0);
		lot15.setLotStatus(LotStatus.AVAILABLE);
		lot15.setCity(lviv);
		lots.add(lot15);

		lot16 = new Lot();
		lot16.setLotName("Sony Xperia Z1");
		lot16.setCategory(electronicsSmartphones);
		lot16.setSeller(userAdmin);
		lot16.setPrice(1.0);
		lot16.setLotStatus(LotStatus.AVAILABLE);
		lot16.setCity(lviv);
		lots.add(lot16);

		lot17 = new Lot();
		lot17.setLotName("Canon Eos 100D");
		lot17.setCategory(electronicsCameras);
		lot17.setSeller(userAdmin);
		lot17.setPrice(1.0);
		lot17.setLotStatus(LotStatus.AVAILABLE);
		lot17.setCity(lviv);
		lots.add(lot17);

		lot18 = new Lot();
		lot18.setLotName("Canon PowerShot G15");
		lot18.setCategory(electronicsSmartphones);
		lot18.setSeller(userAdmin);
		lot18.setPrice(1.0);
		lot18.setLotStatus(LotStatus.AVAILABLE);
		lot18.setCity(lviv);
		lots.add(lot18);

		lot19 = new Lot();
		lot19.setLotName("Sony Cyber-Shot DSC-RX10");
		lot19.setCategory(electronicsCameras);
		lot19.setSeller(userAdmin);
		lot19.setPrice(1.0);
		lot19.setLotStatus(LotStatus.AVAILABLE);
		lot19.setCity(lviv);
		lots.add(lot19);

		lot20 = new Lot();
		lot20.setLotName("Sony Alpha7");
		lot20.setCategory(electronicsCameras);
		lot20.setSeller(userAdmin);
		lot20.setPrice(1.0);
		lot20.setLotStatus(LotStatus.AVAILABLE);
		lot20.setCity(lviv);
		lots.add(lot20);

		lot21 = new Lot();
		lot21.setLotName("Tavria Slavuta");
		lot21.setCategory(motorsCars);
		lot21.setSeller(userAdmin);
		lot21.setPrice(583.0);
		lot21.setCity(lviv);
		lots.add(lot21);

		lot22 = new Lot();
		lot22.setLotName("Ferrari F40");
		lot22.setCategory(motorsCars);
		lot22.setSeller(userAdmin);
		lot22.setPrice(1200.0);
		lot22.setCity(lviv);
		lots.add(lot22);

		lot23 = new Lot();
		lot23.setLotName("Lamborghini Diablo");
		lot23.setCategory(motorsCars);
		lot23.setSeller(userAdmin);
		lot23.setPrice(14.0);
		lot23.setCity(kyiv);
		lots.add(lot23);

		lot24 = new Lot();
		lot24.setLotName("Maserati Granturismo");
		lot24.setCategory(motorsCars);
		lot24.setSeller(userAdmin);
		lot24.setPrice(678.0);
		lot24.setCity(ternopil);
		lots.add(lot24);

		lot25 = new Lot();
		lot25.setLotName("iPhone 3g");
		lot25.setCategory(electronicsSmartphones);
		lot25.setSeller(userAdmin);
		lot25.setPrice(20.0);
		lot25.setCity(kyiv);
		lots.add(lot25);

		lot26 = new Lot();
		lot26.setLotName("iPhone 3gs");
		lot26.setCategory(electronicsSmartphones);
		lot26.setSeller(userAdmin);
		lot26.setPrice(15.0);
		lot26.setCity(lviv);
		lots.add(lot26);

		lot27 = new Lot();
		lot27.setLotName("iPhone 4g");
		lot27.setCategory(electronicsSmartphones);
		lot27.setSeller(userAdmin);
		lot27.setPrice(100.0);
		lot27.setCity(kyiv);
		lots.add(lot27);

		lot28 = new Lot();
		lot28.setLotName("iPhone 4gs");
		lot28.setCategory(electronicsSmartphones);
		lot28.setSeller(userAdmin);
		lot28.setPrice(150.0);
		lot28.setCity(ternopil);
		lots.add(lot28);

		lot29 = new Lot();
		lot29.setLotName("iPad 3g");
		lot29.setCategory(electronicsSmartphones);
		lot29.setSeller(userAdmin);
		lot29.setPrice(50.0);
		lot29.setCity(lviv);
		lots.add(lot29);

		lot30 = new Lot();
		lot30.setLotName("Canon EOS 450d");
		lot30.setCategory(electronicsCameras);
		lot30.setSeller(userAdmin);
		lot30.setPrice(300.0);
		lot30.setCity(kyiv);
		lots.add(lot30);

		lot31 = new Lot();
		lot31.setLotName("Nikon D3100");
		lot31.setCategory(electronicsCameras);
		lot31.setSeller(userAdmin);
		lot30.setPrice(230.0);
		lot31.setCity(lviv);
		lots.add(lot31);

		lot32 = new Lot();
		lot32.setLotName("Nikon D5300");
		lot32.setCategory(electronicsCameras);
		lot32.setSeller(userAdmin);
		lot32.setPrice(57.0);
		lot32.setCity(ternopil);
		lots.add(lot32);

		lot33 = new Lot();
		lot33.setLotName("Nikon D5300");
		lot33.setCategory(electronicsCameras);
		lot33.setSeller(userAdmin);
		lot33.setPrice(98.0);
		lot33.setCity(kyiv);
		lots.add(lot33);

		lot34 = new Lot();
		lot34.setLotName("iPhone 4g");
		lot34.setCategory(electronicsSmartphones);
		lot34.setSeller(userAdmin);
		lot34.setPrice(100.0);
		lot34.setCity(ternopil);
		lots.add(lot34);

		return lots;
	}

	private List<City> cities() {

		List<City> cities = new ArrayList<City>();

		defaultCity = new City();
		defaultCity.setCityName("Select city");
		Set<Location> defaultLocations = new HashSet<>();
		defaultLocations.add(defaultLocation);
		defaultCity.setLocations(defaultLocations);
		cities.add(defaultCity);

		Set<Location> lvivLocations = new HashSet<>();
		lvivLocations.add(lvivLocation);
		lviv = new City();
		lviv.setLocations(lvivLocations);
		lviv.setCityName("Lviv");
		cities.add(lviv);

		Set<Location> kyivLocations = new HashSet<>();
		kyivLocations.add(kyivLocation);
		kyiv = new City();
		kyiv.setLocations(kyivLocations);
		kyiv.setCityName("Kyiv");
		cities.add(kyiv);

		Set<Location> ternopilLocations = new HashSet<>();
		ternopilLocations.add(ternopilLocation);
		ternopil = new City();
		ternopil.setLocations(ternopilLocations);
		ternopil.setCityName("Ternopil");
		cities.add(ternopil);

		return cities;
	}

	private List<Location> locations() {

		List<Location> locations = new ArrayList<Location>();

		defaultLocation = new Location();
		defaultLocation = new Location();
		defaultLocation.setCity(defaultCity);
		locations.add(defaultLocation);

		lvivLocation = new Location();
		lvivLocation.setUser(userAdmin);
		lvivLocation.setStreet("Pasichna");
		lvivLocation.setApartment(55);
		lvivLocation.setBuilding(98);
		lvivLocation.setCity(lviv);

		kyivLocation = new Location();
		kyivLocation.setUser(superman);
		kyivLocation.setApartment(111);
		kyivLocation.setBuilding(89);
		kyivLocation.setStreet("Maidan Nezalegnosti");
		kyivLocation.setCity(kyiv);

		ternopilLocation = new Location();
		ternopilLocation.setUser(ironman);
		ternopilLocation.setCity(ternopil);
		ternopilLocation.setStreet("Sadova");
		ternopilLocation.setBuilding(9);
		ternopilLocation.setApartment(13);

		locations.add(ternopilLocation);
		locations.add(kyivLocation);
		locations.add(lvivLocation);

		return locations;
	}

	/**
	 * Adds images to all input lots
	 * 
	 * @param lots
	 * @return processedLots
	 */
	private List<Lot> pictures(List<Lot> lots) {
		List<Lot> processedLots = new ArrayList<>();
		InputStream inputStream = null;

		for (Lot lot : lots) {
			File folder = null;
			String path = this.getClass().getClassLoader().getResource("")
					.getPath();
			path = path.replaceAll("target/classes/", "");
			path = path + "src/main/webapp/resources/images/initImages/";
			path = path + lot.getLotName();
			folder = new File(path);
			File[] files = null;
			if (folder != null) {
				files = folder.listFiles();
			}

			File file;

			if (files != null) {
				for (int index = 0; index < files.length; index++) {
					Picture pic = new Picture();
					file = files[index];
					try {
						inputStream = new FileInputStream(file);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					byte[] data;
					if (inputStream != null) {
						try {
							data = IOUtils.toByteArray(inputStream);
							pic.setPicture(data);
							lot.addPicture(pic);
						} catch (IOException e) {
						}
					}
				}
			}
			processedLots.add(lot);
		}

		if (processedLots.size() == lots.size()) {
			return processedLots;
		} else {
			return lots;
		}
	}

}
