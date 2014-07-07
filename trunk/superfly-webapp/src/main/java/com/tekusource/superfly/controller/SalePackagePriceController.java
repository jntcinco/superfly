package com.tekusource.superfly.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.superfly.model.SalePackage;
import com.tekusource.superfly.service.SalePackagePriceService;

@Controller
@RequestMapping(value="/salePackagePrice")
public class SalePackagePriceController extends AbstractController {

	@Autowired
	private SalePackagePriceService salePackagePriceService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView pageInitializer(HttpSession httpSession, ModelMap model) {
		model.addAttribute("salePackages", (List<SalePackage>) salePackagePriceService.getSalePackages());
		return new ModelAndView(SALE_PACKAGE_PAGE, model);
	}
	
	@RequestMapping(value="/addSalePackage", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, ? extends Object> addBloodline(@ModelAttribute("salePackage") SalePackage salePackage, BindingResult result) {
		String message = "";
		if(salePackagePriceService.isSalePackageSave(salePackage)) {
			message = "Successfully saved.";
		} else {
			message = "Failed to save.";
		}
		return Collections.singletonMap("message", message);
	}
}
