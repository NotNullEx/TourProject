package com.tour.project.adminservice;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admindao.AdminUserUpdateDao;

@Service
public class AdminUserUpdateServiceImpl implements AdminUserUpdateService{

	@Autowired
	private AdminUserUpdateDao dao;
	
	@Override
	public int adminUserUpdate(HashMap<String, String> map) throws Exception {
		int result = 0;
		result = dao.adminUserUpdate(map);
		return result;
	}

}
