package com.tekusource.superfly.controller;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.superfly.SuperflyMessages;
import com.tekusource.superfly.model.BloodLine;
import com.tekusource.superfly.model.Content;
import com.tekusource.superfly.service.ContentService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController extends AbstractController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pageInitializer(HttpSession httpSession, ModelMap model) {
		if(isUserSessionValid(httpSession)) {
			viewName = ADMIN_PAGE;
		} else {
			viewName = LOGIN_PAGE;
		}
		model.addAttribute("userSession", userSession);
		return new ModelAndView(viewName, model);
	}
	
	@RequestMapping(value = "/cms_page", method=RequestMethod.GET)
	public ModelAndView cmsPageInitializer(HttpSession httpSession, ModelMap model) {
		if(isUserSessionValid(httpSession)) {
			model.addAttribute("bloodLine", new BloodLine());
			viewName = CMS_PAGE;
		} else {
			viewName = LOGIN_PAGE;
		}
		model.addAttribute("userSession", userSession);
		return new ModelAndView(viewName, model);
	}

	@RequestMapping(value="/saveContent", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, ? extends Object> saveContent(@RequestParam String contentParam, @RequestParam String page) {
		String message = "";
		Content content = (Content) contentService.getContentByPage(page);
		if(content != null) {
			content.setTextContent(contentParam);
			contentService.updateContent(content);
			message = SuperflyMessages.CONTENT_UPDATE;
		} else {
			content = new Content();
			content.setPage(page);
			content.setTextContent(contentParam);
			contentService.saveContent(content);
			message = SuperflyMessages.CONTENT_SAVE;
		}
		return Collections.singletonMap("message", message);
	}

	@RequestMapping(value="/getContent", method=RequestMethod.GET)
	@ResponseBody
	public Content getContent(@RequestParam String page) {
		return (Content) contentService.getContentByPage(page);
	}
}
