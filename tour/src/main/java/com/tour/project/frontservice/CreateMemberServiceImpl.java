package com.tour.project.frontservice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.frontdao.CreateMemberDao;



@Service
public class CreateMemberServiceImpl implements CreateMemberService {
	@Autowired
	private CreateMemberDao createDao;
	
	@Override
	public int create(Map<String, Object> map) {
		int result = createDao.create(map);
		return result;
	}

	@Override
	public int overlap(String id) {
		int result = createDao.overlap(id);
		return result;
	}
}
