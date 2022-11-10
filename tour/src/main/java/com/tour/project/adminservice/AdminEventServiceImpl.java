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
	public int create(EventVO vo) {	
		return eventDao.create(vo);
	}
	
	@Override
	public List<EventVO> listAll() {
		return eventDao.listAll();
	}

	@Override
	public int getTotal() {
		return eventDao.getTotal();
	}

	@Override
	public List<EventVO> listByCode(String code) {
		return eventDao.listByCode(code);
	}

	@Override
	public int deleteAll() {
		int result = 0;
		result = eventDao.deleteAll();
		return result;
	}
	
}
