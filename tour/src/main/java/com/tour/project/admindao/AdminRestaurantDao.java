package com.tour.project.admindao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.adminvo.RestaurantVO;

@Repository
public class AdminRestaurantDao {
	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public int create(Map<String, Object> map) {
		int result = 0;
		result = sqltemplate.insert("restaurant.create", map);
		return result;
	}
	
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
	
	public List<RestaurantVO> listAll() {
		List<RestaurantVO> result = new ArrayList<RestaurantVO>();
		result = sqltemplate.selectList("restaurant.listAll");
		return result;
	}
	
	public List<RestaurantVO> listOne(String search) {
		List<RestaurantVO> result = new ArrayList<RestaurantVO>();
		result = sqltemplate.selectList("restaurant.listOne", search);
		return result;
	}
	
	public List<RestaurantVO> listBySection(String adress) {
		List<RestaurantVO> result = new ArrayList<RestaurantVO>();
		result = sqltemplate.selectList("restaurant.listBySection", adress);
		return result;
	}
	
	public int reviseAll(Map<String, Object> map) {
		int result = 0;
		result = sqltemplate.update("restaurant.reviseAll", map);
		return result;
	}
	
	public List<Map<String, Object>> getpostCodeList() throws Exception {
		List<Map<String, Object>> postCodeList = sqltemplate.selectList("restaurant.postCodeList");
		
		return postCodeList;
	}
}