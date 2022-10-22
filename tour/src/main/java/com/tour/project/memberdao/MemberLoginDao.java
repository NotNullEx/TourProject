package com.tour.project.memberdao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class MemberLoginDao {

	
	@Autowired 
	private SqlSessionTemplate sqltemplate;
	public int memberLogin(Map<String, Object> map) throws SQLException {
		int rtnVal = sqltemplate.selectOne("member.memberlogin", map);
    	return rtnVal;
	}
}
