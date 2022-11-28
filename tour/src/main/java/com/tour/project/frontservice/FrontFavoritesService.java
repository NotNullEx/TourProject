package com.tour.project.frontservice;

import java.util.List;
import java.util.Map;

import com.tour.project.adminvo.TourVO;

public interface FrontFavoritesService {
	
	public int create(Map<String, Object> map) throws Exception;
	public List<TourVO> tourListByFavorites(int seq) throws Exception;
	public int cancelFavorites(Map<String, Object> map) throws Exception;
	
}
