package com.tour.project.batch.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.bacth.dao.SchedulerDao;

@Service
public class SchedulerServiceImpl implements SchedulerService{

	@Autowired
	private SchedulerDao batchDao;
	
	@Override
	public List<Map<String, Object>> getFavoriteTourList() throws Exception {
		// TODO Auto-generated method stub
		return batchDao.getFavoriteTourList();
	}

	@Override
	public int insertRecommendTourData(List<Map<String, Object>> list) throws Exception {
		// TODO Auto-generated method stub
		return batchDao.insertRecommendTourData(list);
	}

	@Override
	public void deleteRecommendTourData() throws Exception {
		// TODO Auto-generated method stub
		batchDao.deleteRecommendTourData();
	}

}
