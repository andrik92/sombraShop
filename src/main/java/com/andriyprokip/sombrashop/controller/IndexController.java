package com.andriyprokip.sombrashop.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.andriyprokip.sombrashop.manager.interfaces.ICategoryManager;
import com.andriyprokip.sombrashop.manager.interfaces.IDealManager;
import com.andriyprokip.sombrashop.manager.interfaces.ILotManager;
import com.andriyprokip.sombrashop.manager.interfaces.IUserManager;

@Controller
public class IndexController {
	@Autowired
	private IUserManager history;

	@Autowired
	private ICategoryManager categoryManager;

	@Autowired
	private ILotManager lotManager;

	@Autowired
	private IDealManager historyManager;

	@RequestMapping("/")
	public String index(Model model, Principal principal) {
		
		model.addAttribute("lots", lotManager.findAvailableLots());	
		model.addAttribute("subCategories", categoryManager.findAll());
		return "index";
	}
}