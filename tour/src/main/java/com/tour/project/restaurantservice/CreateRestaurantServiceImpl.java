package com.tour.project.restaurantservice;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.restaurantdao.CreateRestaurantDao;

@Service
public class CreateRestaurantServiceImpl implements CreateRestaurantService{

	@Autowired
	private CreateRestaurantDao createDao;

	@Override
	public int create(String map) {
		int result = createDao.create(map);		
		return result;
	}
	




	 
}