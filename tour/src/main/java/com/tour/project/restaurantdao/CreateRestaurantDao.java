package com.tour.project.restaurantdao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CreateRestaurantDao {
	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public int create(String map) {
		int result = 0;
		result = sqltemplate.insert("restaurant.create", map);
		return result;
	}
}