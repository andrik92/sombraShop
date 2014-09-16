package com.andriyprokip.sombrashop.manager.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andriyprokip.sombrashop.dao.CategoryDao;
import com.andriyprokip.sombrashop.dao.CityDao;
import com.andriyprokip.sombrashop.dao.LotDao;
import com.andriyprokip.sombrashop.dao.UserDao;
import com.andriyprokip.sombrashop.entity.BeanForFilter;
import com.andriyprokip.sombrashop.entity.Category;
import com.andriyprokip.sombrashop.entity.City;
import com.andriyprokip.sombrashop.entity.Lot;
import com.andriyprokip.sombrashop.forms.SearchResult;
import com.andriyprokip.sombrashop.manager.interfaces.ICategoryManager;
import com.andriyprokip.sombrashop.manager.interfaces.ILotManager;

@Service
public class LotManager implements ILotManager {

	@Autowired
	private LotDao lotDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CityDao cityDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ICategoryManager categoryManager;

	@Override
	@Transactional
	public void save(List<Lot> lots) {
		lotDao.save(lots);
	}

	@Override
	@Transactional
	public void save(Lot lot) {
		lotDao.save(lot);
	}

	public Page<Lot> findByUser(Integer userId, int pageNumber, int pageSize,
			String sortingColumn) {
		return lotDao.findBySeller(userDao.findOne(userId), new PageRequest(
				pageNumber, pageSize, Sort.Direction.ASC, sortingColumn));
	}

	@Override
	public List<Lot> findAvailableLots() {
		return lotDao.findAvailableLots();
	}

	public Page<Lot> findAvailableLotsByUser(Integer userId, int pageNumber,
			int pageSize, String sortingColumn) {
		return lotDao.findAvailableLotsByUser(userId, new PageRequest(
				pageNumber, pageSize, Sort.Direction.ASC, sortingColumn));
	}

	@Override
	@Transactional
	public void delete(List<Lot> lots) {
		lotDao.delete(lots);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Lot> findAll() {
		return lotDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Lot find(Integer lotId) {
		return lotDao.findOne(lotId);
	}

	@Transactional(readOnly = true)
	@Override
	public Lot findLotByName(String lotName) {
		return lotDao.findLotByName(lotName);
	}

	@Override
	@Transactional
	public void changecategory(Category category, Category category1) {
		lotDao.changecategory(category, category1);
	}

	@Override
	public SearchResult getResultOfSearch(BeanForFilter filter, int pageNumber,
			int pageSize, String sortBy, String direction) {

		Direction d = Sort.Direction.fromString(direction);
		PageRequest request = new PageRequest(pageNumber, pageSize, d, sortBy);

		if (filter.getFilterUse() == 0) {
			return getResultOfSearchWithoutFilters(filter, request);
		} else {
			return getResultOfSearchWithFilters(filter, request);
		}
	}

	private SearchResult getResultOfSearchWithoutFilters(BeanForFilter filter,
			PageRequest request) {
		SearchResult result = new SearchResult();
		String subName = "%" + filter.getSubName().trim() + "%";
		int idCategoryForSearch = filter.getCategoryId();

		List<Category> listCategories = categoryManager
				.getAllSubCategories(categoryDao.findOne(idCategoryForSearch));
		List<Category> listCategoriesForJsp = lotDao
				.getCategoriesOfLotsWithSubName(subName, listCategories);

		Page<Lot> lots;
		lots = lotDao.findLotsByNameInCategories(subName, listCategories,
				request);

		List<City> cities = lotDao.getCitiesOfLotsSubname(subName,
				listCategories);
		Double minPrice = lotDao.getMinPriceOfLotsSubname(subName,
				listCategories);
		Double maxPrice = lotDao.getMaxPriceOfLotsSubname(subName,
				listCategories);

		int minPriceForJSP = minPriceFromDoubleToInt(minPrice);
		int maxPriceForJSP = maxPriceFromDoubleToInt(maxPrice);

		filter.setMin(minPriceForJSP);
		filter.setMax(maxPriceForJSP);
		filter.setMas(new int[] { minPriceForJSP, maxPriceForJSP });

		result.setMinPriceForJsp(minPriceForJSP);
		result.setMaxPriceForJsp(maxPriceForJSP);
		result.setMinPriceSliderForJsp(minPriceForJSP);
		result.setMaxPriceSliderForJsp(maxPriceForJSP);
		result.setLotsList(lots);
		result.setCities(cities);
		result.setListCategoriesForJsp(listCategoriesForJsp);

		return result;
	}

	private SearchResult getResultOfSearchWithFilters(BeanForFilter filter,
			PageRequest request) {
		SearchResult result = new SearchResult();
		String subName = "%" + filter.getSubName().trim() + "%";

		Integer[] idCategories = filter.getIdCategories();
		List<City> cities;
		Page<Lot> lots;
		List<Integer> ids = Arrays.asList(idCategories);
		List<Category> listCategories = categoryDao.findAll(ids);
		List<Category> listCategoriesForJsp;
		int minPriceFilter;
		int maxPriceFilter;
		Double minPrice;
		Double maxPrice;
		Double minPriceRange = lotDao.getMinPriceOfLotsSubname(subName,
				listCategories);
		Double maxPriceRange = lotDao.getMaxPriceOfLotsSubname(subName,
				listCategories);

		if (filter.getMas().length != 0) {
			minPriceFilter = filter.getMas()[0];
			maxPriceFilter = filter.getMas()[1];
		} else {
			minPriceFilter = filter.getMin();
			maxPriceFilter = filter.getMax();
		}

		if (filter.getCityId() == 0) {
			listCategoriesForJsp = lotDao.getCategoriesOfLotsWithSubNamePrice(
					subName, listCategories, minPriceFilter, maxPriceFilter);
			cities = lotDao.getCitiesOfLotsWithPriceSubname(subName,
					listCategories, minPriceFilter, maxPriceFilter);
			lots = lotDao.findLotsByNameInCategoriesFilter(subName,
					listCategories, request, minPriceFilter, maxPriceFilter);
			minPrice = lotDao.getMinPriceOfLotsFilterWithoutCitiesSubname(
					subName, listCategories, minPriceFilter, maxPriceFilter);
			maxPrice = lotDao.getMaxPriceOfLotsFilterWithoutCitiesSubname(
					subName, listCategories, minPriceFilter, maxPriceFilter);
		}

		else {
			cities = new ArrayList<City>();
			cities.add(cityDao.findOne(filter.getCityId()));
			listCategoriesForJsp = lotDao
					.getCategoriesOfLotsWithSubNamePriceCities(subName,
							listCategories, cities, minPriceFilter,
							maxPriceFilter);
			lots = lotDao.findLotsByNameInCategoriesCitiesFilter(subName,
					listCategories, cities, request, minPriceFilter,
					maxPriceFilter);
			minPrice = lotDao.getMinPriceOfLotsFilterWithCitiesSubname(subName,
					listCategories, cities, minPriceFilter, maxPriceFilter);
			maxPrice = lotDao.getMaxPriceOfLotsFilterWithCitiesSubname(subName,
					listCategories, cities, minPriceFilter, maxPriceFilter);
		}

		int minPriceSliderForJSP = minPriceFromDoubleToInt(minPrice);
		int maxPriceSliderForJSP = maxPriceFromDoubleToInt(maxPrice);
		int minPriceForJSP = minPriceFromDoubleToInt(minPriceRange);
		int maxPriceForJSP = maxPriceFromDoubleToInt(maxPriceRange);

		filter.setMin(minPriceSliderForJSP);
		filter.setMax(maxPriceSliderForJSP);
		filter.setMas(new int[] { minPriceSliderForJSP, maxPriceSliderForJSP });
		result.setMinPriceForJsp(minPriceForJSP);
		result.setMaxPriceForJsp(maxPriceForJSP);
		result.setMinPriceSliderForJsp(minPriceSliderForJSP);
		result.setMaxPriceSliderForJsp(maxPriceSliderForJSP);
		result.setLotsList(lots);
		result.setCities(cities);
		result.setListCategoriesForJsp(listCategoriesForJsp);

		return result;
	}

	@Override
	public SearchResult getResultOfSearchInCategory(BeanForFilter filter,
			int pageNumber, int pageSize, String sortBy, String direction) {
		Direction d = Sort.Direction.fromString(direction);
		PageRequest request = new PageRequest(pageNumber, pageSize, d, sortBy);

		Category category = categoryDao.findOne(filter.getCategoryId());

		List<Category> listCategories = categoryManager
				.getAllSubCategories(category);

		if (filter.getFilterUse() == 0) {
			return getResultOfSearchWithoutFiltersInCategory(filter,
					listCategories, request);
		} else {
			return getResultOfSearchWithFiltersInCategory(filter,
					listCategories, request);
		}
	}

	private SearchResult getResultOfSearchWithFiltersInCategory(
			BeanForFilter filter, List<Category> listCategories,
			PageRequest request) {

		SearchResult result = new SearchResult();
		List<City> cities;
		Page<Lot> lots;
		int minPriceFilter;
		int maxPriceFilter;
		Double minPrice;
		Double maxPrice;
		Double minPriceRange = lotDao.getMinPriceOfLots(listCategories);
		Double maxPriceRange = lotDao.getMaxPriceOfLots(listCategories);

		if (filter.getMas().length != 0) {
			minPriceFilter = filter.getMas()[0];
			maxPriceFilter = filter.getMas()[1];
		} else {
			minPriceFilter = filter.getMin();
			maxPriceFilter = filter.getMax();
		}

		if (filter.getCityId() == 0) {
			cities = lotDao.getCitiesOfLotsWithPrice(listCategories,
					minPriceFilter, maxPriceFilter);
			lots = lotDao.lotOfCategoriesFilter(listCategories, minPriceFilter,
					maxPriceFilter, request);
			minPrice = lotDao.getMinPriceOfLotsFilterWithoutCities(
					listCategories, minPriceFilter, maxPriceFilter);
			maxPrice = lotDao.getMaxPriceOfLotsFilterWithoutCities(
					listCategories, minPriceFilter, maxPriceFilter);
		}

		else {
			cities = new ArrayList<City>();
			cities.add(cityDao.findOne(filter.getCityId()));
			lots = lotDao.lotOfCategoriesFilter(listCategories, cities,
					minPriceFilter, maxPriceFilter, request);

			minPrice = lotDao.getMinPriceOfLotsFilter(listCategories, cities,
					minPriceFilter, maxPriceFilter);
			maxPrice = lotDao.getMaxPriceOfLotsFilter(listCategories, cities,
					minPriceFilter, maxPriceFilter);

		}

		int minPriceForJSP = minPriceFromDoubleToInt(minPriceRange);
		int maxPriceForJSP = maxPriceFromDoubleToInt(maxPriceRange);

		int minPriceSliderForJSP = minPriceFromDoubleToInt(minPrice);
		int maxPriceSliderForJSP = maxPriceFromDoubleToInt(maxPrice);

		filter.setMin(minPriceSliderForJSP);
		filter.setMax(maxPriceSliderForJSP);
		filter.setMas(new int[] { minPriceSliderForJSP, maxPriceSliderForJSP });
		result.setMinPriceForJsp(minPriceForJSP);
		result.setMaxPriceForJsp(maxPriceForJSP);
		result.setMinPriceSliderForJsp(minPriceSliderForJSP);
		result.setMaxPriceSliderForJsp(maxPriceSliderForJSP);
		result.setLotsList(lots);
		result.setCities(cities);
		return result;
	}

	private SearchResult getResultOfSearchWithoutFiltersInCategory(
			BeanForFilter filter, List<Category> listCategories,
			PageRequest request) {
		SearchResult result = new SearchResult();
		Page<Lot> lots = lotDao.lotOfCategories(listCategories, request);
		List<City> cities = lotDao.getCitiesOfLots(listCategories);
		Double minPrice = lotDao.getMinPriceOfLots(listCategories);
		Double maxPrice = lotDao.getMaxPriceOfLots(listCategories);
		int minPriceSliderForJSP = minPriceFromDoubleToInt(minPrice);
		int maxPriceSliderForJSP = maxPriceFromDoubleToInt(maxPrice);

		filter.setMin(minPriceSliderForJSP);
		filter.setMax(maxPriceSliderForJSP);
		filter.setMas(new int[] { minPriceSliderForJSP, maxPriceSliderForJSP });
		result.setMinPriceForJsp(minPriceSliderForJSP);
		result.setMaxPriceForJsp(maxPriceSliderForJSP);
		result.setMinPriceSliderForJsp(minPriceSliderForJSP);
		result.setMaxPriceSliderForJsp(maxPriceSliderForJSP);
		result.setLotsList(lots);
		result.setCities(cities);
		return result;
	}

	private int minPriceFromDoubleToInt(Double minPrice) {
		if (minPrice == null) {
			return 0;
		} else {
			return minPrice.intValue();
		}
	}

	private int maxPriceFromDoubleToInt(Double maxPrice) {
		if (maxPrice == null) {
			return 0;
		} else if (maxPrice * 10 % 10 > 0) {
			return 1 + maxPrice.intValue();
		} else {
			return (int) maxPrice.intValue();
		}
	}

	@Override
	public Lot getAvailableLotByUserDateAndName(String lotName, Integer userId) {
		return lotDao.getAvailableLotByUserDateAndName(lotName, userId);
	}

}
