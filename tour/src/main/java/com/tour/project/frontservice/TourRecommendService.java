package com.tour.project.frontservice;

import java.util.List;

import com.tour.project.frontvo.TourRecommendVO;

public interface TourRecommendService {

	public List<TourRecommendVO> getTourRecommendList() throws Exception;
}
