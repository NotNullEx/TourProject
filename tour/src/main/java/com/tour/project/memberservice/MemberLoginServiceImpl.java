package com.tour.project.memberservice;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.memberdao.MemberLoginDao;

@Service
public class MemberLoginServiceImpl implements MemberLoginService {
	@Autowired
	private MemberLoginDao dao;
	
	@Override
	public int login(Map<String, Object> map) {
		int result = 0;
		try {
			result = dao.memberLogin(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return result;
	}
}
