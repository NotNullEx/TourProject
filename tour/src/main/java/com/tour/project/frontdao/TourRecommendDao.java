package com.tour.project.frontdao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.frontvo.TourRecommendVO;

@Repository
public class TourRecommendDao {

	@Autowired
	private SqlSessionTemplate sqltemplate;
	
	public List<TourRecommendVO> getTourRecommendList() throws Exception {
		List<TourRecommendVO> list = sqltemplate.selectList("tourrecommend.getTourRecommendList");
		
		return list;
		
	}
}
