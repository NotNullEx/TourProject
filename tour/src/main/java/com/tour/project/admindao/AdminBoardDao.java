package com.tour.project.admindao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminBoardDao {
	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public int create(Map<String, Object> map) {
		int result = 0;
		result = sqltemplate.insert("board.create",map);
		return result;
	}
}
