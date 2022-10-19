package com.tour.project;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tour.project.adminservice.LoginService;

@Controller
public class AdminLoginController {

	@Autowired
	private LoginService service;
	
	@RequestMapping(value = {"/admin/login"})
	public ModelAndView create() {
		return new ModelAndView("admin/admin_login");
	}
	
	@RequestMapping(value = {"/admin/loginOk"})
	public ModelAndView create(@RequestParam Map<String,Object> map ) {
		ModelAndView mav = new ModelAndView();
		int isCreated =  service.login(map);
		if(isCreated == 1) {
			System.out.println("success");
			mav.addObject("result",isCreated);
		}
		else {
			System.out.println("faile");
			mav.setViewName("redirect:/admin/createaccount");
		}
		return mav;
	}
}
