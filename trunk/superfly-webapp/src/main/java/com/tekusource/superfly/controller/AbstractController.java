package com.tekusource.superfly.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.superfly.model.UserSession;

public abstract class AbstractController {

	protected String viewName;

	protected static final String IMAGE_GALLERY_PAGE		= "image_gallery";
	protected static final String SALE_PACKAGE_PAGE			= "sale_package";
	protected static final String HOME_CONTENT_PAGE			= "text_editor";
	protected static final String CMS_PAGE					= "cms_page";
	protected final static String AWARDS_PAGE 				= "awards";
	protected static final String ADMIN_PAGE				= "admin";
	protected static final String LOGIN_PAGE				= "login";
	protected final static String USER_PAGE 				= "user";

	protected static final String PAGE_CATEGORY_AWARDS		= "Awards";
	protected static final String PAGE_CATEGORY_ABOUT		= "About";
	
	public UserSession userSession;
	
	public abstract ModelAndView pageInitializer(HttpSession httpSession, ModelMap model);
	
	public boolean isUserSessionValid(HttpSession httpSession) {
		userSession = (UserSession) httpSession.getAttribute("userSession");
		
		if(userSession != null) {
			return true;
		} else {
			userSession = new UserSession();
			return false;
		}
	}
	
	public UserSession getUserSession() {
		return userSession;
	}
	
	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}
}
