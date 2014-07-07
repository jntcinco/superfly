package com.tekusource.superfly.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.superfly.model.Content;
import com.tekusource.superfly.service.ContentService;

@Controller
public class GuestController extends AbstractController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping(value="/awards", method = RequestMethod.GET)
	public ModelAndView pageInitializer(HttpSession httpSession, ModelMap model) {
		Content content = (Content) contentService.getContentByPage(PAGE_CATEGORY_AWARDS);
		model.addAttribute("awardsContent", content);
		return new ModelAndView(AWARDS_PAGE, model);
	}
}
