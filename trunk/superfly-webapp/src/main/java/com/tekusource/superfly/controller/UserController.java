package com.tekusource.superfly.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.superfly.SuperflyMessages;
import com.tekusource.superfly.model.Content;
import com.tekusource.superfly.model.UserDetail;
import com.tekusource.superfly.model.UserSession;
import com.tekusource.superfly.service.ContentService;
import com.tekusource.superfly.service.UserService;
import com.tekusource.superfly.validator.LoginValidator;
import com.tekusource.superfly.validator.UserValidator;

@Controller
@RequestMapping(value="/user")
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;
	@Autowired
	private ContentService contentService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView pageInitializer(HttpSession httpSession, ModelMap model) {
		Content content = (Content) contentService.getContentByPage(PAGE_CATEGORY_ABOUT);
		model.addAttribute("aboutContent", content);
		model.addAttribute("userSession", new UserSession());
		return new ModelAndView(USER_PAGE, model);
	}
	
	@RequestMapping(value="/sign_out", method=RequestMethod.GET)
	public ModelAndView signOut(HttpSession httpSession, ModelMap model) {
		if(httpSession != null) {
			httpSession.invalidate();
			model.addAttribute("userSession", new UserSession());
		}
		return new ModelAndView(LOGIN_PAGE);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(HttpSession httpSession, @ModelAttribute("userSession") UserSession userSession, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		LoginValidator loginValidator = new LoginValidator();
		loginValidator.validate(userSession, result);
		
		if(result.hasErrors()) {
			viewName = LOGIN_PAGE;
		} else {
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("username", userSession.getUsername());
			values.put("password", userSession.getPassword());
			UserDetail user = (UserDetail) userService.getUserBy(values);
			if(user == null) {
				model.put("loginMessage", SuperflyMessages.INVALID_CREDENTIALS);
				viewName = LOGIN_PAGE;
			} else {
				userSession = new UserSession(user);
				httpSession.setAttribute("userSession", userSession);
				viewName = ADMIN_PAGE;
			}
		}
		return new ModelAndView(viewName, model);
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("userSession") UserSession userSession, BindingResult result) {
		Map<String, String> errorMessages = new HashMap<String, String>();
		UserValidator userValidator = new UserValidator();
		userValidator.validate(userSession, result);
		
		if(result.hasErrors()) {
			viewName = USER_PAGE;
		} else {
			String username = userSession.getUsername();
			if(userService.isUserExist(username, userSession.getPassword())) {
				errorMessages.put("registrationMessage", SuperflyMessages.USER_EXIST);
				viewName = USER_PAGE;
			} else {
				UserDetail user = new UserDetail();
				user.setLastname(userSession.getLastname());
				user.setFirstname(userSession.getFirstname());
				user.setEmail(userSession.getEmail());
				user.setUsername(userSession.getUsername());
				user.setPassword(userSession.getPassword());
				userService.save(user);
				viewName = ADMIN_PAGE;
			}
		}
		return new ModelAndView(viewName, errorMessages);
	}
}
