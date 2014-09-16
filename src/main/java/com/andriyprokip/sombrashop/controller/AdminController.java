package com.andriyprokip.sombrashop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.andriyprokip.sombrashop.entity.Category;
import com.andriyprokip.sombrashop.entity.City;
import com.andriyprokip.sombrashop.entity.User;
import com.andriyprokip.sombrashop.manager.interfaces.ICityManager;
import com.andriyprokip.sombrashop.manager.interfaces.ICategoryManager;
import com.andriyprokip.sombrashop.manager.interfaces.ILotManager;
import com.andriyprokip.sombrashop.manager.interfaces.IUserManager;

@Controller
public class AdminController {
	@Autowired
	private IUserManager userManager;
	@Autowired
	private ICategoryManager categoryManager;
	@Autowired
	private ILotManager lotManager;
	@Autowired
	private ICityManager cityManager;

	@ModelAttribute("category")
	public Category construct() {
		return new Category();
	}

	@ModelAttribute("city")
	public City constructorCity() {
		return new City();
	}

	@ModelAttribute("user")
	public User constructorUser() {
		return new User();
	}

	@RequestMapping(value = "/admEditUsers/{pageNumber}")
	public String allUsers(Model model, @PathVariable int pageNumber) {
		pageNumber -= 1;
		int pageSize = 10;
		Page<User> pageusers = userManager.findAll(pageNumber, pageSize);
		int current = pageusers.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, pageusers.getTotalPages());
		List<User> lotsList = pageusers.getContent();

		model.addAttribute("users", lotsList);
		model.addAttribute("pageusers", pageusers);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		return "admEditUsers";
	}

	@RequestMapping(value = "/deactivate/{cityId}", method = RequestMethod.GET)
	public String deactivateCity(@PathVariable int cityId, Model model) {
		cityManager.deactivateById(cityId);
		return "redirect:/admEditCities";
	}

	@RequestMapping(value = "/edit/{login}", method = RequestMethod.GET)
	public String editUser(@PathVariable String login, Model model) {
		model.addAttribute("login", login);
		return "redirect:/adm1#5";
	}

	@RequestMapping(value = "/adm1", method = RequestMethod.GET)
	public String delCategory(@RequestParam("login") String login, Model model) {
		User i = userManager.findByLogin(login);
		model.addAttribute(userManager.findAll());
		model.addAttribute("login", i);
		return "admEditUsers";
	}

	@RequestMapping(value = "/activate/{cityId}", method = RequestMethod.GET)
	public String activateCity(@PathVariable int cityId, Model model) {
		cityManager.activateById(cityId);
		return "redirect:/admEditCities";
	}

	@RequestMapping(value = "/delete/{categoryName}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable String categoryName, Model model)
			throws Exception {
		try {
			categoryManager.delete(categoryManager
					.findBynameCategory(categoryName));
			return "redirect:/admEditCategory";
		} catch (Exception e) {
			model.addAttribute("categoryName", categoryName);
			return "redirect:/delleteCategory#98";
		}
	}

	@RequestMapping(value = "/deleteCity/{cityId}", method = RequestMethod.GET)
	public String deleteCity(@PathVariable int cityId) {
		cityManager.delete(cityManager.findByIdCity(cityId));
		return "redirect:/admEditCities";
	}

	@RequestMapping(value = "/addCat/{categoryId}/a", method = RequestMethod.GET)
	public String addCategory(@PathVariable Integer categoryId, Model model) {
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("categories", categoryManager.findAll());
		return "redirect:/admEditCategoryh#addcat";
	}

	@RequestMapping(value = "/admEditCategory", method = RequestMethod.GET)
	public String allCategories(Model model) {
		model.addAttribute("categories", categoryManager.findParentCategories());
		return "admEditCategory";
	}

	@RequestMapping(value = "/admEditCategoryh", method = RequestMethod.GET)
	public String adusers1(@RequestParam("categoryId") Integer categoryId,
			Model model) {
		model.addAttribute("categories", categoryManager.findParentCategories());
		model.addAttribute("categoryId", categoryId);
		return "admEditCategory";
	}

	@RequestMapping(value = "/admEditCities", method = RequestMethod.GET)
	public String adusers2(Model model) {
		model.addAttribute("cities", cityManager.findAllCity());
		return "admEditCities";
	}

	@RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
	public String deleteCategoryName(
			@RequestParam("categoryName") String categoryName, Model model) {
		model.addAttribute("categories", categoryManager.findParentCategories());
		model.addAttribute("categorie", categoryManager
				.findAllExceptme(categoryManager
						.findBynameCategory(categoryName)));
		model.addAttribute("categoryName", categoryName);
		return "admEditCategory";
	}

	@RequestMapping(value = "/editLots", method = RequestMethod.POST)
	public String changeLotsCategory(
			@ModelAttribute("category") Category category, BindingResult result) {
		lotManager.changecategory(
				categoryManager.findById(category.getCategoryId()),
				categoryManager.findBynameCategory(category.getCategoryName()));
		categoryManager.delete(categoryManager.findBynameCategory(category
				.getCategoryName()));
		return "redirect:/admEditCategory";
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public String addCategory(
			@Valid @ModelAttribute("category") Category category,
			BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/admEditCategory#88";
		}

		Category childCategory = new Category();
		childCategory.setCategoryName(category.getCategoryName());
		Category parentCategory = categoryManager.findById(category
				.getCategoryId());
		childCategory.setParent(parentCategory);
		if (childCategory.getCategoryName().equals(
				parentCategory.getCategoryName())) {
			return "redirect:/admEditCategory#88";
		}
		categoryManager.save(childCategory);
		return "redirect:/admEditCategory";
	}

	@RequestMapping(value = "/saveCity", method = RequestMethod.POST)
	public String addCity(@ModelAttribute("city") City city,
			BindingResult result) {
		city.setEnabledCity(true);
		cityManager.save(city);
		return "redirect:/admEditCities";
	}

	@RequestMapping(value = "/admDelete", method = RequestMethod.POST)
	public String deactivateUsert(@RequestParam("userId") int userId) {
		userManager.deactivateById(userId);
		return "redirect: /admEditUsers/1";
	}

	@RequestMapping(value = "/admDeactivate/{userId}")
	public String DeactivateUser(@PathVariable int userId) {
		userManager.deactivateById(userId);
		return "redirect:/admEditUsers/1";
	}

	@RequestMapping(value = "/admActivate/{userId}")
	public String ActivateUser(@PathVariable int userId) {
		userManager.activateById(userId);
		return "redirect:/admEditUsers/1";
	}

	@RequestMapping(value = "/editUserDeal", method = RequestMethod.POST)
	public String addCity(@ModelAttribute("user") User user,
			BindingResult result) {
		User uUser = userManager.findById(user.getUserId());
		uUser.setEmail(user.getEmail());
		uUser.setLogin(user.getLogin());
		uUser.setPhoneNumber(user.getPhoneNumber());
		uUser.setFirstName(user.getFirstName());
		uUser.setLastName(user.getLastName());
		userManager.update(uUser);
		return "redirect:/admEditUsers/1";
	}

	@RequestMapping(value = "/searchUsers", method = RequestMethod.POST)
	public String adusers(@ModelAttribute("user") User search,
			BindingResult result, Model model) {
		String substring = "%" + search.getFirstName() + "%";
		boolean paging = true;
		if (search.getUserId() == 1) {
			model.addAttribute("users",
					userManager.findUsersByFirstName(substring));
			model.addAttribute("paging", paging);
			return "admEditUsers";
		}
		if (search.getUserId() == 2) {
			model.addAttribute("paging", paging);
			return "admEditUsers";
		} else {
			model.addAttribute("paging", paging);
			return "admEditUsers";
		}
	}
}