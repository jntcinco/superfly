package com.tekusource.superfly.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tekusource.superfly.model.UserSession;

public class UserValidator implements Validator {
	
	@Override
	public boolean supports(Class clazz) {
		return UserSession.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		UserSession userSession = (UserSession) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required");
		
		String password = userSession.getPassword();
		if(password.isEmpty()) {
			errors.rejectValue("password", "password.required");
		}
		if(userSession.getLastname().isEmpty()) {
			errors.rejectValue("lastname", "lastname.required");
		}
		if(userSession.getFirstname().isEmpty()) {
			errors.rejectValue("firstname", "firstname.required");
		}
		if(userSession.getEmail().isEmpty()) {
			errors.rejectValue("email", "email.required");
		}
		
		if(userSession.getConfirmPassword().isEmpty()) {
			errors.rejectValue("confirmPassword", "confirmPassword.required");
		} else {
			if(!password.equals(userSession.getConfirmPassword())) {
				errors.reject("confirmPassword", "confirmPassword.notMatch");
			}
		}
	}
}
