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
	
	public List<RestaurantVO> listAll() {
		List<RestaurantVO> result = new ArrayList<RestaurantVO>();
		result = sqltemplate.selectList("restaurant.listAll");
		return result;
	}
	
	public List<RestaurantVO> listOne(String code) {
		List<RestaurantVO> result = new ArrayList<RestaurantVO>();
		result = sqltemplate.selectList("restaurant.listOne", code);
		return result;
	}
	
	public List<RestaurantVO> listBySection(String adress) {
		List<RestaurantVO> result = new ArrayList<RestaurantVO>();
		result = sqltemplate.selectList("restaurant.listBySection", adress);
		return result;
	}
}