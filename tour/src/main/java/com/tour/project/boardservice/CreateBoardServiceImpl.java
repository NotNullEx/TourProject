package com.tour.project.boardservice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tour.project.boarddao.CreateBoardDao;

public class CreateBoardServiceImpl implements CreateBoardService {
	@Autowired
	private CreateBoardDao createDao;
	
	@Override
	public int create(Map<String, Object> map) {
		int result = createDao.create(map);	
		return result;
	}

}
