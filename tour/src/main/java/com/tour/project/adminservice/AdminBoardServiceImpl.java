package com.tour.project.adminservice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admindao.AdminBoardDao;
@Service
public class AdminBoardServiceImpl implements AdminBoardService {
	@Autowired
	private AdminBoardDao createDao;
	
	@Override
	public int create(Map<String, Object> map) {
		int result = createDao.create(map);	
		return result;
	}

}
