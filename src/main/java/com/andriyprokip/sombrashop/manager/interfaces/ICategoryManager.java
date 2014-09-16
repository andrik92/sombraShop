package com.andriyprokip.sombrashop.manager.interfaces;

import java.util.List;

import com.andriyprokip.sombrashop.entity.Category;

public interface ICategoryManager {

	List<Category> findParentCategories();

	List<Category> findSubCategoryById(Integer parentId);

	void addCategory(String nameCategory, int idParent);

	List<Category> findAllSortC();

	List<Category> findSubCategoryByParent(Category category);

	List<Category> getAllParents(Category category);

	List<Category> getAllSubCategories(Category category);

	public List<Category> findAll();

	public void save(Category category);

	public Category findById(Integer id);

	public void delete(Category category);

	public Category findBynameCategory(String categoryName);

	public List<Category> findAllExceptme(Category category);
}
