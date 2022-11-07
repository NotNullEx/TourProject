package com.tour.project.adminservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admindao.AdminEventDao;
import com.tour.project.adminvo.EventVO;

@Service
public class AdminEventServiceImpl implements AdminEventService {
	@Autowired
	private AdminEventDao eventDao;
	
	@Override
	public int create(Map<String, Object> map) {	
		return eventDao.create(map);
	}
	
	@Override
	public List<EventVO> list(/* Map<String, Object> map */) {
		return eventDao.list(/* map */);
	}
	
}
