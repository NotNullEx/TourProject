package com.tour.project.frontdao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberUpdateDao {
	
	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public int memberUpdate(HashMap<String, String> map){
		int result = 0;
		result = sqltemplate.update("member.memberUpdate",map);
		
		return result;
		
	}
}
