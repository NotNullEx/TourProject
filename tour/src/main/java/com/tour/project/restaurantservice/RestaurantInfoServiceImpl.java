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
	public List<RestaurantVO> list(int index) {
		List<RestaurantVO> result = infoDao.list(index);
		return result;
	}
	




	 
}