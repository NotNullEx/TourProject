package com.tour.project;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CreateAccount {
	
	@RequestMapping(value = {"/admin/create"})
	public ModelAndView create(Locale locale, Model model) {
		
		ModelAndView mav = new ModelAndView("/admin/account");

		return mav;
		
	}
	
	@RequestMapping(value = {"/admin/createUser"})
	public ModelAndView createUser(@RequestParam String id, @RequestParam String pass, Locale locale, Model model) {
		
		ModelAndView mav = new ModelAndView("/admin/createuser");
		

		return mav;
		
	}
}
