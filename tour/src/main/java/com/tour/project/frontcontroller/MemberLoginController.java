package com.tour.project.frontcontroller;

import java.util.List;
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

import com.tour.project.common.ResultSendToClient;
import com.tour.project.common.UtilClass;
import com.tour.project.frontservice.MemberLoginService;
import com.tour.project.frontvo.MemberVO;

@Controller
public class MemberLoginController {
	private static final Logger log = LoggerFactory.getLogger(MemberLoginController.class);
	@Autowired
	private MemberLoginService service;

	@RequestMapping(value = { "/front/login" })
	public ModelAndView create() {
		return new ModelAndView("front/member_login");
	}

	@RequestMapping(value = { "/front/loginOk" })
	public void loginOk(@RequestParam Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		String id = map.get("id").toString();
		String pass = map.get("password").toString();
		String password = UtilClass.SHA256(pass);
		map.put("password", password);

		int isCreated = service.login(map);
		if (isCreated == 1) {
			request.getSession().setAttribute("MEMBER_ID", id);
			String memberId = (String) request.getSession().getAttribute("MEMBER_ID");
			MemberVO rtnVal = service.memberInfo(memberId);
			int mem_seq = rtnVal.getMem_seq();
			request.getSession().setAttribute("FRONT_US_SEQ", mem_seq);
			System.out.println("success");

			ResultSendToClient.onlyResultTo(response, isCreated);
		} else {
			System.out.println("faile");
		}

	}

	@RequestMapping(value = { "/front/logOut" })
	public void logOut(Locale locale, HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getSession().invalidate();
			response.sendRedirect("/front/login");
		} catch (Exception ex) {
			log.error(UtilClass.exceptionToString((Exception) ex));
			// TODO: handle exception
		}

	}

}
