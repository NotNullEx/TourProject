package com.tour.project.adminservice;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admindao.AdminRestaurantDao;
import com.tour.project.adminvo.RestaurantVO;

@Service
public class AdminRestaurantServiceImpl implements AdminRestaurantService{

	@Autowired
	private AdminRestaurantDao restaurantDao;

	@Override
	public int create(Map<String, Object> map) {
		int result = restaurantDao.create(map);		
		return result;
	}
	
	@Override
	public int deleteAll() {
		int result = 0;
		result = restaurantDao.deleteAll();
		return result;
	}

	@Override
	public int deleteOne(String code) {
		int result = 0;
		result = restaurantDao.deleteOne(code);
		return result;
	}

	@Override
	public List<RestaurantVO> listAll() {
		List<RestaurantVO> result = restaurantDao.listAll();
		return result;
	}

	@Override
	public List<RestaurantVO> listOne(String search) {
		List<RestaurantVO> result = restaurantDao.listOne(search);
		return result;
	}

	@Override
	public List<RestaurantVO> listBySection(String adress) {
		List<RestaurantVO> result = restaurantDao.listBySection(adress);
		return result;
	}
	
	@Override
	public int reviseAll(Map<String, Object> map) {
		int result = 0;
		result = restaurantDao.reviseAll(map);
		return result;
	}
	 
}