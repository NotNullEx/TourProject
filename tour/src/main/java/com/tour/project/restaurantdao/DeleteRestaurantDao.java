package com.tour.project.restaurantdao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeleteRestaurantDao {
	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public int deleteAll() {
		int result = 0;
		result = sqltemplate.delete("restaurant.deleteAll");
		return result;
	}
	
	public int deleteOne(String code) {
		int result = 0;
		result = sqltemplate.delete("restaurant.deleteOne",code);
		return result;
	}
}
