package com.tour.project.frontservice;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admindao.AdminUserUpdateDao;
import com.tour.project.frontdao.MemberUpdateDao;

@Service
public class MemberUpdateServiceImpl implements MemberUpdateService{

	@Autowired
	private MemberUpdateDao dao;
	
	@Override
	public int memberUpdate(HashMap<String, String> map) throws Exception {
		int result = 0;
		result = dao.memberUpdate(map);
		return result;
	}

}
