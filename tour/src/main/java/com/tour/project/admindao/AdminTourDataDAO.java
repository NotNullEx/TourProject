package com.tour.project.admindao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.adminvo.TourVO;
@Repository
public class AdminTourDataDAO {

	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public int tourInsert(TourVO vo) {
		int result = -1;
		result = sqltemplate.insert("tour.tourInsert", vo);
		return result;
		
	}
	public int tourInsert(HashMap<String, Object> map) {
		int result = -1;
		result = sqltemplate.insert("tour.tourDataInsert", map);
		return result;
		
	}
	
	public List<TourVO> tourList(){
		List<TourVO> tourList = sqltemplate.selectList("tour.tourList");
		return tourList;
	}
	
	public List<TourVO> tourOneList(String tour_seq){
		List<TourVO> tourList = sqltemplate.selectList("tour.tourOneList",tour_seq);
		return tourList;
	}
	
	public int tourUpdateData(HashMap<String, Object> map) {
		int result = -1;
		result = sqltemplate.update("tour.tourUpdateData",map);
		return result;
	}
	
	public int tourDeleteDate(String seq) {
		int result = -1;
		result = sqltemplate.delete("tour.tourDeleteData",seq);
		return result;
	}
}
