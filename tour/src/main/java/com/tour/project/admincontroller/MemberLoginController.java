package com.tour.project.admincontroller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tour.project.adminservice.LoginService;
import com.tour.project.common.ResultSendToClient;
import com.tour.project.memberservice.MemberLoginService;

@Controller
public class MemberLoginController {

	@Autowired
	private MemberLoginService service;

	@RequestMapping(value = { "/front/login" })
	public ModelAndView create() {
		return new ModelAndView("front/member_login");
	}

	@RequestMapping(value = { "/member/loginOk" })
	public void loginOk(@RequestParam Map<String, Object> map, HttpServletResponse response) {

		int isCreated = service.login(map);
		if (isCreated == 1) {
			System.out.println("success");

			ResultSendToClient.onlyResultTo(response, isCreated);
		} else {
			System.out.println("faile");
		}
	}

}
