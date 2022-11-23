package com.tour.project.frontdao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FrontComentsDao {
	@Autowired
	private SqlSessionTemplate sqltemplate;
	
	public int create(Map<String, Object> map) throws Exception {
		return sqltemplate.insert("coments.create", map);
	}
}
