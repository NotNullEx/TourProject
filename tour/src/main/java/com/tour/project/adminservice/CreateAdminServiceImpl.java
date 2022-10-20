package com.tour.project.adminservice;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admindao.CreateAdminDao;

@Service
public class CreateAdminServiceImpl implements CreateAdminService{

	@Autowired
	private CreateAdminDao createDao;

	@Override
	public int create(Map<String, Object> map) {
		int result = createDao.create(map);		
		return result;
	}
	




	 
}
