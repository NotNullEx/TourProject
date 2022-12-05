package com.tour.project.admindao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class AdmingLoginDao {

	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public int adminLogin(Map<String, Object> map) throws SQLException {
		
		int rtnVal = sqltemplate.selectOne("tour.adminLogin", map);
    	return rtnVal;
	}
	
	public Map<String, Object> getAdminInfo(String id) throws SQLException{
		
		Map<String, Object> result = sqltemplate.selectOne("tour.adminInfo",id);
		return result;
	}
	
	public int daminDel(String id) throws Exception{
		int result = sqltemplate.update("tour.adminDel",id);
		
		return result;		
	}
}
