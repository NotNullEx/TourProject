package com.tour.project.admindao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.adminvo.RestaurantVO;
import com.tour.project.common.vo.PageCriteriaVO;

@Repository
public class AdminRestaurantDao {
	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public int create(Map<String, Object> map) {
		int result = 0;
		result = sqltemplate.insert("restaurant.create", map);
		return result;
	}
	
	public int deleteOne(String code) {
		int result = 0;
		result = sqltemplate.delete("restaurant.deleteOne",code);
		return result;
	}
	
	public List<RestaurantVO> listAll(PageCriteriaVO cri) throws Exception{
		List<RestaurantVO> result = new ArrayList<RestaurantVO>();
		result = sqltemplate.selectList("restaurant.listAll", cri);
		return result;
	}
	
	public List<RestaurantVO> listOne(String search) {
		List<RestaurantVO> result = new ArrayList<RestaurantVO>();
		result = sqltemplate.selectList("restaurant.listOne", search);
		return result;
	}
	
	public List<RestaurantVO> listBySection(HashMap<String, Object> map) {
		List<RestaurantVO> result = new ArrayList<RestaurantVO>();
		result = sqltemplate.selectList("restaurant.listBySection", map);
		return result;
	}
	
	public int reviseAll(Map<String, Object> map) {
		int result = 0;
		result = sqltemplate.update("restaurant.reviseAll", map);
		return result;
	}
	
	public int getRestaurantTotal() throws Exception{
		int result = 0;
		result = sqltemplate.selectOne("restaurant.getRestaurantTotal");
		return result;
	}
	
	public int getRestaurantTotalBySection(String adress) throws Exception{
		int result = 0;
		result = sqltemplate.selectOne("restaurant.getRestaurantTotalBySection", adress);
		return result;
	}
}