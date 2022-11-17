package com.tour.project.admincontroller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tour.project.adminservice.LoginService;
import com.tour.project.common.ResultSendToClient;
import com.tour.project.common.UtilClass;

@Controller
//@RequestMapping("/test")
public class AdminLoginController {
	private static final Logger log = LoggerFactory.getLogger(AdminLoginController.class);
	
	@Autowired
	private LoginService service;
	
	@RequestMapping(value = {"/admin/login"})
	public ModelAndView create() {
		return new ModelAndView("admin/admin_login");
	}
	
	@RequestMapping(value = {"/admin/loginOk"})
	public void loginOk(@RequestParam Map<String,Object> map,HttpServletResponse response, HttpServletRequest request) {
		String id = map.get("id").toString();
		String pass = map.get("password").toString();
		String pasword = UtilClass.SHA256(pass);
		map.put("password", pasword);
		int isCreated =  service.login(map);
		if(isCreated == 1) {
			request.getSession().setAttribute("ADMIN_ID", id);
			String adminId = (String) request.getSession().getAttribute("ADMIN_ID");
			Map<String, Object> rtnVal = service.amindInfo(adminId);
			int admin_seq = (Integer) rtnVal.get("admin_seq");
			request.getSession().setAttribute("SESSION_US_SEQ", admin_seq);
			System.out.println("success");
			
			ResultSendToClient.onlyResultTo(response, isCreated);
		}
		else {
			System.out.println("faile");
		}
	}
	//로그아웃
	@RequestMapping(value = { "/admin/logOut" })
	public void logOut(Locale locale, HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getSession().invalidate();
			response.sendRedirect("/admin/login");
		} catch (Exception ex) {
			log.error(UtilClass.exceptionToString((Exception) ex));
		}
	}
}
