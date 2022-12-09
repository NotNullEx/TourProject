package com.tour.project.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminIntreceptor implements HandlerInterceptor {

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		if (user_id == null || "".equals(user_id)) {
			response.sendRedirect("/admin/login");
			return false;
		}
		return true;
    }
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		if (user_id == null || "".equals(user_id)) {
			response.sendRedirect("/admin/login");
		}
	}
	
}
