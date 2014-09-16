package com.andriyprokip.sombrashop.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andriyprokip.sombrashop.entity.City;
import com.andriyprokip.sombrashop.entity.Location;
import com.andriyprokip.sombrashop.entity.User;
import com.andriyprokip.sombrashop.manager.interfaces.ICityManager;
import com.andriyprokip.sombrashop.manager.interfaces.ILocationManager;
import com.andriyprokip.sombrashop.manager.interfaces.IUserManager;

@Controller
@RequestMapping("/editProfile")
public class EditUserController {
	
	@Autowired
	private IUserManager userManager;
	@Autowired
	private ILocationManager locationManager;
	@Autowired
	private ICityManager cityManager;

	@ModelAttribute("user")
	public User currentUser(Principal principal) {
		String login = principal.getName();
		return userManager.findByLogin(login);
	}

	@RequestMapping
	public String showProfile(Model model, Principal principal) {
		String login = principal.getName();
		User user = userManager.findByLogin(login);
		List<City> cityList = new ArrayList<>();
		City city = new City();
		city.setCityName("Select city");
		cityList.add(city);
		cityList.addAll(cityManager.findAllCity());
		model.addAttribute("cityList", cityList);
		model.addAttribute("user", user);
		return "editProfile";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String editProfile(@Valid @ModelAttribute("user") User user,
			BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/editProfile.html?success=false";
		}
		Location location = user.getLocation();
		locationManager.save(location);
		userManager.update(user);
		return "redirect:/editProfile.html?success=true";
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	String changePassword(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, Principal principal) {
		User user = userManager.findByLogin(principal.getName());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (encoder.matches(oldPassword, user.getPassword())) {
			user.setPassword(newPassword);
			userManager.save(user);
		}
		return "redirect:/editProfile.html";
	}

	@RequestMapping("/change-password/correctOldPassword")
	@ResponseBody
	public String availablePassword(@RequestParam String oldPassword,
			Principal principal) {
		User user = userManager.findByLogin(principal.getName());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Boolean correct = encoder.matches(oldPassword, user.getPassword());
		return correct.toString();
	}
}
