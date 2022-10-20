package com.tour.project.memberservice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.memberdao.CreateMemberDao;



@Service
public class CreateMemberServiceImpl implements CreateMemberService {
	@Autowired
	private CreateMemberDao createDao;
	
	@Override
	public boolean create(Map<String, Object> map) {
		int result = createDao.create(map);
		return result == 1;
	}
}
