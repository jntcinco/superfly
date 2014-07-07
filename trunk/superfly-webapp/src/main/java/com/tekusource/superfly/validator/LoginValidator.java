package com.tekusource.superfly.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tekusource.superfly.model.UserSession;

public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return UserSession.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		UserSession userSession = (UserSession) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required");
		
		if(userSession.getPassword().isEmpty()) {
			errors.rejectValue("password", "password.required");
		}
	}
}
