package com.andriyprokip.sombrashop.forms;

import java.util.List;

import org.springframework.data.domain.Page;

import com.andriyprokip.sombrashop.entity.Category;
import com.andriyprokip.sombrashop.entity.City;
import com.andriyprokip.sombrashop.entity.Lot;

public class SearchResult {
	private int minPriceForJsp;
	private int maxPriceForJsp;
	private int minPriceSliderForJsp;
	private int maxPriceSliderForJsp;
	private Page<Lot> lotsList;
	private List<City> cities;
	private List<Category> listCategoriesForJsp;

	public int getMinPriceForJsp() {
		return minPriceForJsp;
	}

	public void setMinPriceForJsp(int minPriceForJsp) {
		this.minPriceForJsp = minPriceForJsp;
	}

	public int getMaxPriceForJsp() {
		return maxPriceForJsp;
	}

	public void setMaxPriceForJsp(int maxPriceForJsp) {
		this.maxPriceForJsp = maxPriceForJsp;
	}

	public int getMinPriceSliderForJsp() {
		return minPriceSliderForJsp;
	}

	public void setMinPriceSliderForJsp(int minPriceSliderForJsp) {
		this.minPriceSliderForJsp = minPriceSliderForJsp;
	}

	public int getMaxPriceSliderForJsp() {
		return maxPriceSliderForJsp;
	}

	public void setMaxPriceSliderForJsp(int maxPriceSliderForJsp) {
		this.maxPriceSliderForJsp = maxPriceSliderForJsp;
	}

	public Page<Lot> getLotsList() {
		return lotsList;
	}

	public void setLotsList(Page<Lot> lotsList) {
		this.lotsList = lotsList;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public List<Category> getListCategoriesForJsp() {
		return listCategoriesForJsp;
	}

	public void setListCategoriesForJsp(List<Category> listCategoriesForJsp) {
		this.listCategoriesForJsp = listCategoriesForJsp;
	}

}
