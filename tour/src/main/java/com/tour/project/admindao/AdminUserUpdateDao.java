package com.tour.project.admindao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminUserUpdateDao {
	
	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public int adminUserUpdate(HashMap<String, String> map){
		int result = 0;
		result = sqltemplate.update("tour.adminUpdate",map);
		
		return result;
		
	}
}
