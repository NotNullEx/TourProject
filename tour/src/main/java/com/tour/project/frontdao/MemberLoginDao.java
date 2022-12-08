package com.tour.project.frontdao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.frontvo.MemberVO;
@Repository
public class MemberLoginDao {

	
	@Autowired 
	private SqlSessionTemplate sqltemplate;
	public int memberLogin(Map<String, Object> map) throws SQLException {
		int rtnVal = sqltemplate.selectOne("member.memberLogin", map);
    	return rtnVal;
	}
	
	public MemberVO getmemberInfo(String id) throws SQLException{
		MemberVO result = sqltemplate.selectOne("member.select_detail",id);
		return result;
	}
	
	public int memDel(String id) throws Exception{
		int result = sqltemplate.update("member.memDel",id);
		
		return result;		
	}
}
