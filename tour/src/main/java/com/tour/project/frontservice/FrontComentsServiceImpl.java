package com.tour.project.frontservice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.frontdao.FrontComentsDao;
@Service
public class FrontComentsServiceImpl implements FrontComentsService {
	@Autowired
	private FrontComentsDao comentsDao;
	
	@Override
	public int create(Map<String, Object> map) throws Exception {
		return comentsDao.create(map);
	}

}
