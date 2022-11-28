package com.tour.project.bacth.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SchedulerDao {

	@Autowired
	private SqlSessionTemplate sqltemplate;
	
	public List<Map<String, Object>> getFavoriteTourList() throws Exception {
		
		List<Map<String, Object>> list = sqltemplate.selectList("scheduler.getFavoriteTourList");
	
		return list;
	}
	
	public int insertRecommendTourData(List<Map<String, Object>> list) throws Exception {
		int result = sqltemplate.insert("scheduler.insertRecommendTourData",list);
		
		return result;
	}
	
	public void deleteRecommendTourData() throws Exception {
		sqltemplate.delete("scheduler.deleteRecommendTourData");
	}
}
