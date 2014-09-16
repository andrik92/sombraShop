
package com.andriyprokip.sombrashop.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andriyprokip.sombrashop.entity.Lot;
import com.andriyprokip.sombrashop.manager.interfaces.ICartManager;
//import com.andriyprokip.sombrashop.manager.interfaces.IBidManager;
import com.andriyprokip.sombrashop.manager.interfaces.IUserManager;
import com.andriyprokip.sombrashop.util.PagingUtil;

@Controller
public class CartController {

	@Autowired
	IUserManager userManager;
	@Autowired
	ICartManager cartManager;

	private final String directionAsc = "ASC";
	private final String directionDesc = "DESC";
	private final String defaultSortItemValue = "lotId";
	private final String defaultCountItemsValue = "10";

	PagingUtil<Lot> page;

	@RequestMapping("/cart/{pageNumber}")
	public String allBids(
			Model model,
			Principal principal,
			RedirectAttributes redirectAttributes,
			@PathVariable Integer pageNumber,
			@RequestParam(required = false, defaultValue = defaultSortItemValue, value = "sorterItem") String sorterItem,
			@RequestParam(required = false, defaultValue = defaultCountItemsValue, value = "countItems") String countItems,
			@RequestParam(required = false, defaultValue = directionAsc, value = "direction") String direction) {

		String login = principal.getName();
		if (direction.equals(directionAsc))
			direction = directionDesc;
		else
			direction = directionAsc;

			page = new PagingUtil<Lot>(cartManager.findAllCart(login,
					pageNumber, Integer.parseInt(countItems), "cartId",
					direction));

		model.addAttribute("user", userManager.findByLogin(login));
		model.addAttribute("content", page.getPage());
		model.addAttribute("beginIndex", page.getBegin());
		model.addAttribute("endIndex", page.getEnd());
		model.addAttribute("currentIndex", page.getCurrent());
		model.addAttribute("sorterItem", sorterItem);
		model.addAttribute("countItems", countItems);
		model.addAttribute("direction", direction);
		return "cart";
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String removeManagerFromList(
			Model model,
			Principal principal,
			@RequestParam(value = "lotId", required = false, defaultValue = "") int lotId) {
		String login = principal.getName();
		cartManager.deleteCart(login, lotId);
		return "redirect:/cart/1";

	}
}
