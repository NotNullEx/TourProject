package com.tour.project.adminservice;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admindao.AdminRestaurantDao;
import com.tour.project.adminvo.RestaurantVO;
import com.tour.project.common.vo.PageCriteriaVO;

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
	public int deleteOne(String code) {
		int result = 0;
		result = restaurantDao.deleteOne(code);
		return result;
	}

	@Override
	public List<RestaurantVO> listAll(PageCriteriaVO cri) throws Exception{
		List<RestaurantVO> result = restaurantDao.listAll(cri);
		return result;
	}

	@Override
	public List<RestaurantVO> listOne(String search) {
		List<RestaurantVO> result = restaurantDao.listOne(search);
		return result;
	}

	@Override
	public List<RestaurantVO> listBySection(HashMap<String, Object> map) {
		List<RestaurantVO> result = restaurantDao.listBySection(map);
		return result;
	}
	
	@Override
	public int reviseAll(Map<String, Object> map) {
		int result = 0;
		result = restaurantDao.reviseAll(map);
		return result;
	}

	@Override
	public int getRestaurantTotal() throws Exception {
		return restaurantDao.getRestaurantTotal();
	}
	 
}