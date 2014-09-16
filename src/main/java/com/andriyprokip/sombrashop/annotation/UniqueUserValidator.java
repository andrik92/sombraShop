package com.andriyprokip.sombrashop.annotation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.andriyprokip.sombrashop.dao.UserDao;
import com.andriyprokip.sombrashop.entity.User;

public class UniqueUserValidator implements
		ConstraintValidator<UniqueUser, User> {

	private Pattern pattern;
	private Matcher matcher;
	private final String PHONE_NUMBER = "^[0-9]{10,10}$";

	@Autowired
	private UserDao userDao;
	@Autowired
	private MessageSource messageSource;

	@Override
	public void initialize(UniqueUser constraintAnnotation) {
		pattern = Pattern.compile(PHONE_NUMBER);
	}

	@Override
	public boolean isValid(User user, ConstraintValidatorContext context) {

		if (userDao == null) {
			return true;
		}

		boolean validMail = validEmail(user, context);
		boolean validPhoneNumber = validPhoneNumber(user, context);
		boolean validLogin = validLogin(user, context);
		return (validMail && validPhoneNumber && validLogin);

	}

	private boolean validPhoneNumber(User user,
			ConstraintValidatorContext context) {
		String phoneNumber = user.getPhoneNumber();
		if (!correctPhoneNumber(phoneNumber)) {
			context.buildConstraintViolationWithTemplate(
					"Please put correct phone number")
					.addPropertyNode("phoneNumber").addConstraintViolation();
			return false;
		}

		User tempUser = userDao.findByPhoneNumber(phoneNumber);
		boolean isValid = true;
		if (tempUser != null
				&& (user.getUserId() == null || !tempUser.getUserId().equals(
						user.getUserId()))) {
			isValid = false;
			context.buildConstraintViolationWithTemplate(
					"User with such phoneNumber allready exists")
					.addPropertyNode("phoneNumber").addConstraintViolation();
		}
		return isValid;
	}

	private boolean validEmail(User user, ConstraintValidatorContext context) {
		String email = user.getEmail();
		User tempUser = userDao.findByEmail(email);
		boolean isValid = true;
		if (tempUser != null
				&& (user.getUserId() == null || !tempUser.getUserId().equals(
						user.getUserId()))) {
			isValid = false;
			context.buildConstraintViolationWithTemplate(
					"User with such email allready exists")
					.addPropertyNode("email").addConstraintViolation();
		}
		return isValid;
	}

	private boolean validLogin(User user, ConstraintValidatorContext context) {

		String login = user.getLogin();
		User tempUser = userDao.findByLogin(login);
		boolean isValid = true;
		if (tempUser != null
				&& (user.getUserId() == null || !tempUser.getUserId().equals(
						user.getUserId()))) {
			isValid = false;
			context.buildConstraintViolationWithTemplate(
					"User with such login already exists")
					.addPropertyNode("login").addConstraintViolation();
		}
		return isValid;
	}

	private boolean correctPhoneNumber(String regex) {
		matcher = pattern.matcher(regex);
		return matcher.matches();
	}
}
