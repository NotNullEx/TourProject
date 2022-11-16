package com.tour.project.frontdao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberFavoriteDao {
	@Autowired
	private SqlSessionTemplate sqltemplate;
	
	public int favorite(HashMap<String, Object> map ) {
		int result = 0;
		result = sqltemplate.insert("member.favorites", map);
		
		return result;
		
	}
}
