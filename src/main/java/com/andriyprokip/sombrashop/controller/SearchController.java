package com.andriyprokip.sombrashop.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.andriyprokip.sombrashop.entity.BeanForFilter;
import com.andriyprokip.sombrashop.entity.Category;
import com.andriyprokip.sombrashop.entity.Lot;
import com.andriyprokip.sombrashop.forms.SearchResult;
import com.andriyprokip.sombrashop.manager.interfaces.ICityManager;
import com.andriyprokip.sombrashop.manager.interfaces.ICategoryManager;
import com.andriyprokip.sombrashop.manager.interfaces.ILotManager;
import com.andriyprokip.sombrashop.util.PagingUtil;

@SessionAttributes("filter")
@ControllerAdvice
@RequestMapping
public class SearchController {

	@Autowired
	private ICategoryManager categoryManager;

	@Autowired
	private ILotManager lotManager;

	@Autowired
	private ICityManager cityManager;

	PagingUtil<Lot> page;

	@ModelAttribute("filter")
	public BeanForFilter getTest() {
		return new BeanForFilter();
	}

	@ModelAttribute("allParents")
	public List<Category> getAllParents() {
		return categoryManager.findParentCategories();
	}

	/**
	 * The method responses at "search" submit button and sends data to
	 * searchLots().
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/searchLots", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("filter") BeanForFilter category) {
		return "redirect:searchLots/1";
	}

	/**
	 * The method responses at url and forms selectLots.jsp.
	 * 
	 * @param filter
	 * @param model
	 * @param principal
	 * @param pageNumber
	 * @param sortBy
	 * @param pageSize
	 * @param direction
	 * @return
	 */
	@RequestMapping(value = "/searchLots/{pageNumber}", method = RequestMethod.GET)
	public String searchLots(
			@ModelAttribute("filter") BeanForFilter filter,
			Model model,
			Principal principal,
			@PathVariable("pageNumber") int pageNumber,
			@RequestParam(required = false, defaultValue = "date", value = "sortBy") String sortBy,
			@RequestParam(required = false, defaultValue = "10", value = "countOnPage") int pageSize,
			@RequestParam(required = false, defaultValue = "DESC", value = "direction") String direction) {
		pageNumber -= 1;
		SearchResult result = lotManager.getResultOfSearch(filter, pageNumber,
				pageSize, sortBy, direction);

		page = new PagingUtil<Lot>(result.getLotsList());

		model.addAttribute("sortBy", sortBy);
		model.addAttribute("direction", direction);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("beginIndex", page.getBegin());
		model.addAttribute("endIndex", page.getEnd());
		model.addAttribute("currentIndex", page.getCurrent());
		model.addAttribute("totalPages", result.getLotsList().getTotalPages());
		model.addAttribute("minPrice", result.getMinPriceForJsp());
		model.addAttribute("maxPrice", result.getMaxPriceForJsp());
		model.addAttribute("minPriceSlider", result.getMinPriceSliderForJsp());
		model.addAttribute("maxPriceSlider", result.getMaxPriceSliderForJsp());
		model.addAttribute("lotsList", result.getLotsList().getContent());
		model.addAttribute("cities", result.getCities());
		model.addAttribute("categoriesLots", result.getListCategoriesForJsp());
		return "searchLots";
	}

	/**
	 * The method respond at "filter" submit button and sends data to
	 * searchLots().
	 * 
	 * @param filt
	 * @return
	 */
	@RequestMapping(value = "/searchLotsFilter", method = RequestMethod.POST)
	public String submitFormFilter(@ModelAttribute("filter") BeanForFilter filt) {
		return "redirect:searchLots/1?sortBy=" + filt.getSortBy()
				+ "&direction=" + filt.getDirection() + "&countOnPage="
				+ filt.getPageSize();
	}
}
