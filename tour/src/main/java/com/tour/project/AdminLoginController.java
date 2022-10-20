package com.tour.project;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tour.project.adminservice.LoginService;
import com.tour.project.common.ResultSendToClient;

@Controller
public class AdminLoginController {

	@Autowired
	private LoginService service;
	
	@RequestMapping(value = {"/admin/login"})
	public ModelAndView create() {
		return new ModelAndView("admin/admin_login");
	}
	
	@RequestMapping(value = {"/admin/loginOk"})
	public void loginOk(@RequestParam Map<String,Object> map,HttpServletResponse response ) {
		
		int isCreated =  service.login(map);
		if(isCreated == 1) {
			System.out.println("success");
			
			ResultSendToClient.onlyResultTo(response, isCreated);
		}
		else {
			System.out.println("faile");
		}
	}
}
