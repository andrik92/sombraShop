package com.andriyprokip.sombrashop.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.andriyprokip.sombrashop.entity.Lot;
import com.andriyprokip.sombrashop.manager.interfaces.ICartManager;
import com.andriyprokip.sombrashop.manager.interfaces.IDealManager;
import com.andriyprokip.sombrashop.manager.interfaces.ILotManager;

@Controller
public class DealController {

	@Autowired
	IDealManager dealManager;
	
	@Autowired
	ICartManager cartManager;

	@Autowired
	ILotManager lotManager;

	@RequestMapping(value = "/deal", method = RequestMethod.GET)
	public String confirmPurchase(
			Model model,
			Principal principal,
			@RequestParam(value = "lotId", required = false, defaultValue = "") int lotId) {
		String login = principal.getName();
		Lot lot = lotManager.find(lotId);
		dealManager.addDeal(login, lot);
		cartManager.deleteCart(login, lotId);
		return "redirect:/cart/1";
	}
}
