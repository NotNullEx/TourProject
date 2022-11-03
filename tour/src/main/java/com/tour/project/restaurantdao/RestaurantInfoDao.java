package com.tour.project.restaurantdao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.restaurantvo.RestaurantVO;

@Repository
public class RestaurantInfoDao {
	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public List<RestaurantVO> list() {
		List<RestaurantVO> result = new ArrayList<RestaurantVO>();
		result = sqltemplate.selectList("restaurant.list");
		return result;
	}
	
	public List<RestaurantVO> listOne(String search) {
		List<RestaurantVO> result = new ArrayList<RestaurantVO>();
		result = sqltemplate.selectList("restaurant.list", search);
		return result;
	}
}