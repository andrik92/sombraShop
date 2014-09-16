package com.andriyprokip.sombrashop.entity;

public class BeanForFilter {

	private int cityId;
	private int min;
	private int max;
	private int categoryId;
	private int[] mas;
	private String sortBy;
	private String direction;
	private int pageSize;
	private String subName;
	private String price;
	private Integer[] idCategories;
	private int filterUse;

	public String getPrice() {
		return price;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getFilterUse() {
		return filterUse;
	}

	public void setFilterUse(int filterUse) {
		this.filterUse = filterUse;
	}

	public Integer[] getIdCategories() {
		return idCategories;
	}

	public void setIdCategories(Integer[] idCategories) {
		this.idCategories = idCategories;
	}

	public BeanForFilter() {
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int[] getMas() {
		return mas;
	}

	public void setMas(int[] name) {
		this.mas = name;
	}

	@Override
	public String toString() {
		return "String for Bean";
	}
}
