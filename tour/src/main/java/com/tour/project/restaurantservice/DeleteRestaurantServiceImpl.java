package com.tour.project.restaurantservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.restaurantdao.DeleteRestaurantDao;

@Service
public class DeleteRestaurantServiceImpl implements DeleteRestaurantService {
	@Autowired
	DeleteRestaurantDao deleteDao;
	
	@Override
	public int delete() {
		int result = 0;
		result = deleteDao.delete();
		return result;
	}

}
