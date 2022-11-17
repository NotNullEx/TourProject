package com.tour.project.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class AdminIntreceptor implements HandlerInterceptor{

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		if (user_id == null || "".equals(user_id)) {
			response.sendRedirect("test/admin/login");
			return false;
		}
		return true;
    }
}
