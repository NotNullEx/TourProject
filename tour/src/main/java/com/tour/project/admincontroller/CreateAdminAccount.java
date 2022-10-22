package com.tour.project.admincontroller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tour.project.adminservice.CreateAdminService;
import com.tour.project.common.ResultSendToClient;

@Controller
public class CreateAdminAccount {
	@Autowired
	private CreateAdminService service;
	
	@RequestMapping(value = {"/admin/createaccount"})
	public ModelAndView create() {
		return new ModelAndView("admin/createaccount");
	}
	
	@RequestMapping(value = {"/admin/createaccountOK"})
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
