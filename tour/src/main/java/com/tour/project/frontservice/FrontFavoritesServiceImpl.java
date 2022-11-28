package com.tour.project.frontservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.adminvo.TourVO;
import com.tour.project.frontdao.FrontFavoritesDao;

@Service
public class FrontFavoritesServiceImpl implements FrontFavoritesService {
	@Autowired
	private FrontFavoritesDao favoritesDao;

	@Override
	public int create(Map<String, Object> map) throws Exception {
		return favoritesDao.create(map);
	}

	@Override
	public List<TourVO> tourListByFavorites(int seq) throws Exception {
		return favoritesDao.tourListByFavorites(seq);
	}

	@Override
	public int cancelFavorites(Map<String, Object> map) throws Exception {
		return favoritesDao.cancelFavorites(map);
	}
	
}
