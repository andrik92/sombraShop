package com.andriyprokip.sombrashop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.andriyprokip.sombrashop.annotation.UniqueCategory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@NamedQueries({

		@NamedQuery(name = "findSubcategories", query = "select elements(parent.children)"
				+ " from Category parent where categoryName = :categoryName"),

		@NamedQuery(name = "Category.findAllParentCategories", query = "select distinct l from "
				+ "Category l join l.children  "),

		@NamedQuery(name = "findAllCategories", query = "from Category"),

		@NamedQuery(name = "Category.findParentCategories", query = "select cat from Category cat "
				+ "where cat.parent is null"),

		@NamedQuery(name = "Category.findSubCategoryById", query = "select cat from Category cat "
				+ "where cat.parent.categoryId = :parentId"),

		@NamedQuery(name = "Category.findAllSortC", query = "select cat from Category cat  "
				+ "order by cat.categoryName"),

		@NamedQuery(name = "Category.findBynameCategory", query = "from Category where categoryName = :categoryName"),

		@NamedQuery(name = "Category.findAllExceptme", query = "select u from Category u"
				+ " where not u=:category and u not in (select elements(p.children) "
				+ "from Category p  where p=:category or p.parent in(select distinct c "
				+ "from Category c join   c.children))"),

		@NamedQuery(name = "Category.findSubCategoryByParent", query = "select cat from Category cat "
				+ "where cat.parent = :category")

})
@UniqueCategory(message = "")
@Table(name = "CATEGORY")
public class Category extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name = "CATEGORY_ID")
	private Integer categoryId;

	@ManyToOne()
	@JoinColumn(name = "PARENT_ID", insertable = true, updatable = false)
	@JsonBackReference
	private Category parent;

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "parent_id")
	@JsonManagedReference
	private List<Category> children = new ArrayList<>();

	@Column(name = "CATEGORY_NAME", unique = true)
	private String categoryName;

	@Column(name = "CATEGORY_NAME_UA")
	private String categoryNameUA;

	public Category() {
	}

	public Category(Category parent, String categoryName) {
		this.parent = parent;
		this.categoryName = categoryName;
	}

	public void addSubcategory(Category subcategory) {
		subcategory.setParent(this);
		children.add(subcategory);
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryNameUA() {
		return categoryNameUA;
	}

	public void setCategoryNameUA(String categoryNameUA) {
		this.categoryNameUA = categoryNameUA;
	}

	public String toString() {
		return "" + categoryName;
	}

}
