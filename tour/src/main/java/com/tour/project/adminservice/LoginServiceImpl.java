package com.tour.project.adminservice;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admindao.AdmingLoginDao;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private AdmingLoginDao dao;
	
	@Override
	public int login(Map<String, Object> map) {
		int result = 0;
		try {
			result = dao.adminLogin(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return result;
	}
}
