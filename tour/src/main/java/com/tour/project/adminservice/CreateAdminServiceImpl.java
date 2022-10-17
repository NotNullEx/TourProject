package com.tour.project.adminservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admindao.CreateAdminDao;
import com.tour.project.adminvo.AdminVO;
@Service
public class CreateAdminServiceImpl implements CreateAdminService{

	@Autowired
	private CreateAdminDao createDao;

	@Override
	public boolean create(Map<String, Object> map) {
		int result = createDao.create(map);		
		return result==1;
	}
	




	 
}
