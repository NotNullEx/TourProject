package com.tour.project.frontcontroller;


import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tour.project.common.ResultSendToClient;
import com.tour.project.memberservice.CreateMemberService;


@Controller
public class CreateMemberController {
	@Autowired
	private CreateMemberService service;
	
	@RequestMapping(value = "/front/createMember", method = RequestMethod.GET)
	public ModelAndView create() {
		return new ModelAndView("front/createMember");
	}
	
	@RequestMapping(value = {"/front/createMemberOK"})
	public void create(@RequestParam Map<String,Object> map, HttpServletResponse response) {
		int isCreated =  service.create(map);
		if(isCreated ==1) {
			System.out.println("success");
			ResultSendToClient.onlyResultTo(response, isCreated);
		}
		else {
			System.out.println("faile");
		}
	}
}

