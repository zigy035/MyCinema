package com.mycinema.web.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycinema.web.dao.AuthUserDAO;
import com.mycinema.web.form.RegisterFormBean;

public class AuthUserValidator implements Validator {
	
	private AuthUserDAO authUserDAO;
	
	public boolean supports(Class<?> clazz) {
		return RegisterFormBean.class.equals(clazz);
	}

	public void validate(Object object, Errors errors) {
		RegisterFormBean formBean = (RegisterFormBean) object;
		
		if (StringUtils.isBlank(formBean.getFirstName())) {
			errors.rejectValue("firstName", "authuser.firstname.required");
		}
		if (StringUtils.isBlank(formBean.getLastName())) {
			errors.rejectValue("lastName", "authuser.lastname.required");
		}
		if (StringUtils.isBlank(formBean.getUsername())) {
			errors.rejectValue("username", "authuser.username.required");
		}
		else if (authUserDAO.getAuthUserByUsername(formBean.getUsername()) != null)
		{
			errors.rejectValue("username", "authuser.username.exists");
		}
		if (StringUtils.isBlank(formBean.getPassword())) {
			errors.rejectValue("password", "authuser.password.required");
		}
		if (StringUtils.isBlank(formBean.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "authuser.confirm.password.required");
		} else if (!formBean.getPassword().equals(formBean.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "authuser.confirm.password.invalid");
		}
	}

	public void setAuthUserDAO(AuthUserDAO authUserDAO) {
		this.authUserDAO = authUserDAO;
	}

}
