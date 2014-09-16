package com.andriyprokip.sombrashop.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andriyprokip.sombrashop.entity.User;
import com.andriyprokip.sombrashop.manager.interfaces.IUserManager;

@Controller
public class UserController {

	@Autowired
	private IUserManager userManager;

	@ModelAttribute("user")
	public User construct() {
		return new User();
	}

	@RequestMapping("/users")
	public String users(Model model) {
		model.addAttribute("users", userManager.findAll());
		return "users";
	}

	@RequestMapping("/user")
	public String detail(Model model, Principal principal) {
		String login = principal.getName();
		model.addAttribute("user", userManager.findByLogin(login));
		return "user-detail";
	}

	@RequestMapping("/register")
	public String showRegister() {
		return "userRegister";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user,
			BindingResult result) {
		if (result.hasErrors()) {
			return "userRegister";
		}
		userManager.save(user);
		return "redirect:/register.html?success=true";
	}

	
	@RequestMapping("/register/availableLogin")
	@ResponseBody
	public String availableLogin( @ModelAttribute("user") User user) {
		Boolean correct = userManager.findByLogin(user.getLogin()) == null;
		return correct.toString();
	}

	@RequestMapping("/register/availableEmail")
	@ResponseBody
	public String availableEmail( @ModelAttribute("user") User user) {
		Boolean correct = userManager.findByEmail(user.getEmail()) == null;
		return correct.toString();
	}
	
	@RequestMapping("/register/availablePhoneNumber")
	@ResponseBody
	public String availablePhoneNumber(@ModelAttribute("user") User user) {
		Boolean correct = userManager.findByPhoneNumber(user.getPhoneNumber()) == null;
		return correct.toString();
	}
}
