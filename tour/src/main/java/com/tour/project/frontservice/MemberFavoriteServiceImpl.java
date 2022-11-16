package com.tour.project.frontservice;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.frontdao.MemberFavoriteDao;

@Service
public class MemberFavoriteServiceImpl implements MemberFavoriteService {

	@Autowired
	private MemberFavoriteDao favoriteDao;
	
	@Override
	public int favorite(HashMap<String, Object> map) {
		int result = 0;
		result = favoriteDao.favorite(map);
		
		return result;
	}
	
}
