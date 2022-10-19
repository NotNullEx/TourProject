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
}
