package com.tour.project.admin_controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tour.project.memberservice.CreateMemberService;


@Controller
public class CreateMemberController {
	@Autowired
	private CreateMemberService service;
	
	@RequestMapping(value = "/front/createMember", method = RequestMethod.GET)
	public ModelAndView create() {
		return new ModelAndView("front/createMember");
	}
	
	@RequestMapping(value = "/front/createMember", method = RequestMethod.POST)
	public ModelAndView create(@RequestParam Map<String,Object> map) {
		ModelAndView mav = new ModelAndView();
		boolean isCreated =  service.create(map);
		if(isCreated) {
			System.out.println("success");
			mav.setViewName("redirect:/");
		}
		else {
			System.out.println("faile");
			mav.setViewName("redirect:/front/createMember");
		}
		return mav;
	}
}

