package com.andriyprokip.sombrashop.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.andriyprokip.sombrashop.dao.CategoryDao;
import com.andriyprokip.sombrashop.entity.Category;

public class UniqueCategoryValidator implements
		ConstraintValidator<UniqueCategory, Category> {

	@Autowired
	private CategoryDao userDao;

	@Override
	public void initialize(UniqueCategory constraintAnnotation) {

	}

	@Override
	public boolean isValid(Category user, ConstraintValidatorContext context) {

		if (userDao == null) {
			return true;
		}

		boolean validLogin = validLogin(user, context);
		return (validLogin);

	}

	private boolean validLogin(Category user, ConstraintValidatorContext context) {
		String categoryName = user.getCategoryName();
		Category tempUser = userDao.findBynameCategory(categoryName);
		boolean isValid = true;
		if (tempUser != null
				&& (user.getCategoryId() == null || !tempUser.getCategoryId()
						.equals(user.getCategoryId()))) {
			isValid = false;
			context.buildConstraintViolationWithTemplate(
					"User with such categoryName allready exists")
					.addPropertyNode("categoryName").addConstraintViolation();
		}
		return isValid;
	}
}
