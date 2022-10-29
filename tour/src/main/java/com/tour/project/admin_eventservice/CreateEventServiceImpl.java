package com.tour.project.admin_eventservice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admin_eventdao.CreateEventDao;

@Service
public class CreateEventServiceImpl implements CreateEventService {
	@Autowired
	private CreateEventDao createDao;
	
	@Override
	public int create(Map<String, Object> map) {	
		return createDao.create(map);
	}
	
}
