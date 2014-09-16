package com.andriyprokip.sombrashop.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.andriyprokip.sombrashop.entity.BeanForFilter;
import com.andriyprokip.sombrashop.entity.Lot;
import com.andriyprokip.sombrashop.forms.SearchResult;
import com.andriyprokip.sombrashop.manager.interfaces.ICityManager;
import com.andriyprokip.sombrashop.manager.interfaces.ICategoryManager;
import com.andriyprokip.sombrashop.manager.interfaces.ILotManager;
import com.andriyprokip.sombrashop.util.PagingUtil;

@SessionAttributes("filterCategory")
@Controller
public class CategoryController {

	@Autowired
	private ICategoryManager categoryManager;

	@Autowired
	private ICityManager cityManager;

	@Autowired
	private ILotManager lotManager;
	
	PagingUtil<Lot> page;

	@ModelAttribute("filterCategory")
	public BeanForFilter getTest() {
		return new BeanForFilter();
	}

	@RequestMapping("/selectCategory/{categoryId}")
	public String selectCategory(@PathVariable int categoryId,
			@ModelAttribute("filterCategory") BeanForFilter filter) {
		filter.setFilterUse(0);
		filter.setCategoryId(categoryId);
		return "redirect:/selectCategory/" + categoryId + "/1";
	}

	@RequestMapping("/selectCategory")
	public String selectAllCategory() {
		return "redirect:/selectCategory/0";
	}

	@RequestMapping("/selectCategory/{categoryId}/{pageNumber}")
	public String selectCategorySort(
			@ModelAttribute("filterCategory") BeanForFilter filter,
			Model model,
			Principal principal,
			@PathVariable("pageNumber") int pageNumber,
			@RequestParam(required = false, defaultValue = "date", value = "sortBy") String sortBy,
			@RequestParam(required = false, defaultValue = "10", value = "countOnPage") int pageSize,
			@RequestParam(required = false, defaultValue = "DESC", value = "direction") String direction) {
		
		pageNumber -= 1;
		SearchResult result = lotManager.getResultOfSearchInCategory(filter,
				pageNumber, pageSize, sortBy, direction);
		
		page = new PagingUtil<Lot>(result.getLotsList());
		
		model.addAttribute("treeOfCategory",
				categoryManager.getAllParents(categoryManager.findById(filter
						.getCategoryId())));
		model.addAttribute("subCategories",
				categoryManager.findSubCategoryById(filter.getCategoryId()));
		model.addAttribute("categoryId", filter.getCategoryId());
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

		return "selectCategory";
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public String testSlider(
			@ModelAttribute("filterCategory") BeanForFilter filter) {
		return "redirect:/selectCategory/" + filter.getCategoryId()
				+ "/1?sortBy=" + filter.getSortBy() + "&direction="
				+ filter.getDirection() + "&countOnPage="
				+ filter.getPageSize();
	}
}