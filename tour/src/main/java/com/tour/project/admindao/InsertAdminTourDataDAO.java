package com.tour.project.admindao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.tour.project.adminvo.TourVO;

public class InsertAdminTourDataDAO {

	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public int tourInsert(TourVO vo) {
		int result = -1;
		result = sqltemplate.insert("tour.tourInsert", vo);
		return result;
		
	}
	
}
