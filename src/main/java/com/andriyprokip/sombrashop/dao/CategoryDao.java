package com.andriyprokip.sombrashop.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andriyprokip.sombrashop.entity.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {
	
	List<Category> findParentCategories();

	List<Category> findSubCategoryById(@Param("parentId") Integer parent);

	List<Category> findAllSortC();
	
	List<Category> findSubCategoryByParent(@Param("category") Category category);
	
	public Category findBynameCategory(@Param("categoryName")String categoryName);
	
	public List<Category> findAllExceptme(@Param("category") Category category);
	
	public Page<Category> findAll( Pageable pageable);
	
}
