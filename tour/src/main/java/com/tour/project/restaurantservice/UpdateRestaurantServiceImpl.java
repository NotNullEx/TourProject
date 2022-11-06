package com.tour.project.restaurantservice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.restaurantdao.UpdateRestaurantDao;
@Service
public class UpdateRestaurantServiceImpl implements UpdateRestaurantService {
	@Autowired
	UpdateRestaurantDao updateDao;
	
	
	@Override
	public int reviseAll(Map<String, Object> map) {
		int result = 0;
		result = updateDao.reviseAll(map);
		return result;
	}

}
