package com.tour.project.frontservice;

import java.util.List;

import com.tour.project.adminvo.TourVO;
import com.tour.project.frontvo.TourRecommendVO;

public interface TourRecommendService {

	public List<TourRecommendVO> getTourRecommendList() throws Exception;
	
	public List<TourVO> getTourRecommendFrontList() throws Exception;
	
	public List<TourVO> getTourRecommendBest() throws Exception;
}
