package com.tour.project.restaurantservice;

import java.util.List;

import com.tour.project.restaurantvo.RestaurantVO;

public interface RestaurantInfoService {
	public List<RestaurantVO> listAll();
	public List<RestaurantVO> listOne(String search);
	public List<RestaurantVO> listBySection(String adress);
}
