package com.andriyprokip.sombrashop.manager.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.andriyprokip.sombrashop.dao.CategoryDao;
import com.andriyprokip.sombrashop.entity.Category;
import com.andriyprokip.sombrashop.manager.interfaces.ICategoryManager;

@Service
public class CategoryManagerImplementation implements ICategoryManager {
	
	@Autowired
	private CategoryDao categoryDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Category> findParentCategories() {
		return categoryDao.findParentCategories();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Category> findSubCategoryById(Integer parent) {
		if (parent == 0) {
			return categoryDao.findParentCategories();
		}
		return categoryDao.findSubCategoryById(parent);
	}

	@Override
	@Transactional
	public void addCategory(String nameCategory, int idParent) {
		Category partntCategory = categoryDao.findOne(idParent);
		categoryDao.save(new Category(partntCategory, nameCategory));
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Category> findAllSortC() {
		return categoryDao.findAllSortC();
	}

	@Override
	public List<Category> findSubCategoryByParent(Category category) {
		return categoryDao.findSubCategoryByParent(category);
	}

	@Override
	public List<Category> getAllParents(Category category) {
		if (category == null) {
			return null;
		}
		List<Category> listCategories = null;
		if (category.getParent() == null) {
			listCategories = new ArrayList<Category>();
			listCategories.add(category);
			return listCategories;
		}
		listCategories = getAllParents(category.getParent());
		listCategories.add(category);
		return listCategories;
	}

	@Override
	public List<Category> getAllSubCategories(Category category) {
		if (category == null) {
			return categoryDao.findAll();
		}
		List<Category> listCategories = new ArrayList<Category>();
		List<Category> tempList = new ArrayList<Category>();
		tempList = categoryDao.findSubCategoryByParent(category);
		
		listCategories.addAll(tempList);
		boolean hasSubcategories = true;
		do {
			hasSubcategories = false;
			List<Category> listCategoryLevel = new ArrayList<Category>();
			for (Category category2 : tempList) {
				if (categoryDao.findSubCategoryByParent(category2) != null) {
					hasSubcategories |= true;
					listCategoryLevel.addAll(categoryDao
							.findSubCategoryByParent(category2));
				}
			}
			if (hasSubcategories) {
				tempList = listCategoryLevel;
				listCategories.addAll(listCategoryLevel);
			}
		} while (hasSubcategories);
		listCategories.add(category);
		return listCategories;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	@Transactional
	public void save(Category category) {
		categoryDao.save(category);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Category findById(Integer id) {
		return categoryDao.findOne(id);
	}

	@Override
	public void delete(Category category) {
		categoryDao.delete(category);

	}

	public Category findBynameCategory(String categoryName) {
		return categoryDao.findBynameCategory(categoryName);
	}

	public List<Category> findAllExceptme(Category category) {
		return categoryDao.findAllExceptme(category);

	}
}
