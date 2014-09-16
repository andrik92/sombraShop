package com.andriyprokip.sombrashop.controller;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.andriyprokip.sombrashop.dao.DealDao;
import com.andriyprokip.sombrashop.entity.Category;
import com.andriyprokip.sombrashop.entity.City;
import com.andriyprokip.sombrashop.entity.Lot;
import com.andriyprokip.sombrashop.entity.User;
//import com.andriyprokip.sombrashop.exception.LotClosedException;
//import com.andriyprokip.sombrashop.exception.LowBidAmountException;
//import com.andriyprokip.sombrashop.exception.OwningLotBidException;
//import com.andriyprokip.sombrashop.exception.SameBidAmountException;
import com.andriyprokip.sombrashop.manager.interfaces.ICityManager;
import com.andriyprokip.sombrashop.manager.interfaces.ICartManager;
import com.andriyprokip.sombrashop.manager.interfaces.ICategoryManager;
import com.andriyprokip.sombrashop.manager.interfaces.IDealManager;
import com.andriyprokip.sombrashop.manager.interfaces.ILotManager;
//import com.andriyprokip.sombrashop.manager.interfaces.INotificationManager;
import com.andriyprokip.sombrashop.manager.interfaces.IUserManager;
import com.andriyprokip.sombrashop.util.PagingUtil;

@Controller
public class LotController {

	@Autowired
	private ILotManager lotManager;
	@Autowired
	private IUserManager userManager;
	@Autowired
	private IDealManager dealManager;
	@Autowired
	private ICartManager cartManager;
	@Autowired
	private ICategoryManager categoryManager;
	@Autowired
	private ICityManager cityManager;
	@Autowired
	private DealDao hDao;

	PagingUtil<Lot> page;

	@RequestMapping("/lots/{lotId}")
	public String lot(Model model, @PathVariable int lotId, Principal principal) {

		Lot lot = lotManager.find(lotId);

		model.addAttribute("treeOfCategory",
				categoryManager.getAllParents(lot.getCategory()));
		model.addAttribute("lot", lot);

		return "lot";
	}

	@RequestMapping(value = "/lots/{lotId}/addToCart")
	public String addToWatchlist(@PathVariable int lotId, Principal principal) {
		cartManager.addToWatchlist(principal.getName(), lotId);
		return "redirect:/lots/" + lotId;
	}

	@RequestMapping(value = "/lots/{lotId}/deleteFromCart")
	public String deleteFromWatchlist(@PathVariable int lotId,
			Principal principal) {
		cartManager.deleteCart(principal.getName(), lotId);
		return "redirect:/lots/" + lotId;
	}

	@ModelAttribute("lot")
	public Lot lot() {
		Lot lot = new Lot(true);
		return lot;
	}

	@RequestMapping(value = "/createLot", method = RequestMethod.GET)
	public String createLot(Model model, Principal principal) {

		List<Category> categories = categoryManager.findAll();
		Boolean hasCategories = !(categories.size() > 0);
		model.addAttribute("categories", categories);
		model.addAttribute("hasCategories", hasCategories);

		List<City> cities = cityManager.findAllCity();
		Boolean hasCities = !(cities.size() > 0);
		model.addAttribute("cities", cities);
		model.addAttribute("hasCities", hasCities);

		return "lotCreation";
	}

	@RequestMapping(value = "/createLot", method = RequestMethod.POST)
	public String processForm(

	@Valid @ModelAttribute("lot") Lot lot, BindingResult result, Model model,
			Principal principal) {
		if (result.hasErrors()) {

			List<Category> categories = categoryManager.findAll();
			Boolean hasCategories = !(categories.size() > 0);

			model.addAttribute("categories", categories);
			model.addAttribute("hasCategories", hasCategories);
			List<City> cities = cityManager.findAllCity();
			Boolean hasCities = !(cities.size() > 0);
			model.addAttribute("cities", cities);
			model.addAttribute("hasCities", hasCities);
			return "lotCreation";
		}
		User user = userManager.findByLogin(principal.getName());
		lot.setPrice(100.0);
		user.addLot(lot);

		userManager.update(user);

		lot = lotManager.getAvailableLotByUserDateAndName(lot.getLotName(),
				user.getUserId());

		model.addAttribute("lot", lot);
		return "redirect:/lots/" + lot.getLotId();

	}

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:m");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		binder.registerCustomEditor(Category.class, null,
				new PropertyEditorSupport() {
					@Override
					public void setAsText(String text) {
						if (!text.equals("null")) {
							Category category = categoryManager
									.findById(Integer.parseInt(text));
							if (category != null) {
								setValue(category);
							} else {
								setValue(null);
							}
						} else {
							setValue(null);
						}

					}
				});
		binder.registerCustomEditor(City.class, null,
				new PropertyEditorSupport() {
					@Override
					public void setAsText(String text) {
						City city;
						if (!text.equals("null")) {
							city = cityManager.findByIdCity(Integer
									.parseInt(text));

							if (city != null) {
								setValue(city);
							}
						} else {
							setValue(null);
						}

					}
				});
	}
}