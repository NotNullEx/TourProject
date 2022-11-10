package com.tour.project.frontdao;

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

	public int overlap(String id) {
		return sqltemplate.selectOne("member.overlap", id);
	}
	
}
