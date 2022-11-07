package com.tour.project.adminservice;

import java.util.List;
import java.util.Map;

import com.tour.project.adminvo.RestaurantVO;

public interface AdminRestaurantService {

	public int create(Map<String, Object> map);
	public int deleteAll();
	public int deleteOne(String code);
	public List<RestaurantVO> listAll();
	public List<RestaurantVO> listOne(String search);
	public List<RestaurantVO> listBySection(String adress);
	public int reviseAll(Map<String, Object> map);

}
