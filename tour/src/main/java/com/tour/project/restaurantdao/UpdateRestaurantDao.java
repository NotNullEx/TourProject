package com.tour.project.restaurantdao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UpdateRestaurantDao {
	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public int reviseAll(Map<String, Object> map) {
		int result = 0;
		result = sqltemplate.update("restaurant.revise", map);
		return result;
	}
}
