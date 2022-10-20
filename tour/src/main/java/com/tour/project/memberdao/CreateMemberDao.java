package com.tour.project.memberdao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CreateMemberDao {
	
	@Autowired
	private SqlSessionTemplate sqltemplate;
	
	public int create(Map<String, Object> map) {
		return sqltemplate.insert("member.create", map);
	}
	
}
