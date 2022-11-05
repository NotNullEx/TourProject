package com.tour.project.restaurantservice;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admindao.CreateAdminDao;
import com.tour.project.restaurantdao.CreateRestaurantDao;
import com.tour.project.restaurantdao.RestaurantInfoDao;
import com.tour.project.restaurantvo.RestaurantVO;

@Service
public class RestaurantInfoServiceImpl implements RestaurantInfoService{

	@Autowired
	private RestaurantInfoDao infoDao;

	@Override
	public List<RestaurantVO> listAll() {
		List<RestaurantVO> result = infoDao.listAll();
		return result;
	}

	@Override
	public List<RestaurantVO> listOne(String search) {
		List<RestaurantVO> result = infoDao.listOne(search);
		return result;
	}

	@Override
	public List<RestaurantVO> listBySection(String adress) {
		List<RestaurantVO> result = infoDao.listBySection(adress);
		return result;
	}
	




	 
}