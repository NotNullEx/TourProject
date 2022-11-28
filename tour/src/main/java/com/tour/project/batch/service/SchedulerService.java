package com.tour.project.batch.service;

import java.util.List;
import java.util.Map;

public interface SchedulerService {

	public List<Map<String, Object>> getFavoriteTourList() throws Exception;
	
	public int insertRecommendTourData(List<Map<String, Object>> list) throws Exception;

	public void deleteRecommendTourData() throws Exception;
}	