package com.tour.project;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tour.project.adminservice.CreateAdminService;

@Controller
public class CreateAdminAccount {
	@Autowired
	private CreateAdminService service;
	
	@RequestMapping(value = "/admin/createaccount", method = RequestMethod.GET)
	public ModelAndView create() {
		return new ModelAndView("admin/createaccount");
	}
	
	@RequestMapping(value = "/admin/createaccount", method = RequestMethod.POST)
	public ModelAndView create(@RequestParam Map<String,Object> map) {
		ModelAndView mav = new ModelAndView();
		boolean isCreated =  service.create(map);
		if(isCreated) {
			System.out.println("success");
			mav.setViewName("redirect:/admin/createaccount");
		}
		else {
			System.out.println("faile");
			mav.setViewName("redirect:/admin/createaccount");
		}
		return mav;
	}
}
