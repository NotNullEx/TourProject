package com.tour.project.adminservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tour.project.adminvo.RestaurantVO;
import com.tour.project.common.vo.PageCriteriaVO;

public interface AdminRestaurantService {

	public int create(Map<String, Object> map);
	public int deleteOne(String code);
	public List<RestaurantVO> listAll(PageCriteriaVO cri) throws Exception;
	public List<RestaurantVO> listOne(String code);
	public List<RestaurantVO> listBySection(HashMap<String, Object> map);
	public int reviseAll(Map<String, Object> map);
	public int getRestaurantTotal() throws Exception;
	public int getRestaurantTotalBySection(String adress) throws Exception;
	public List<RestaurantVO> findByRestaurant(Map<String, Object> map) throws Exception;

}
