package com.tour.project.frontservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.adminvo.TourVO;
import com.tour.project.frontdao.TourRecommendDao;
import com.tour.project.frontvo.TourRecommendVO;

@Service
public class TourRecommendServiceImpl implements TourRecommendService{

	@Autowired
	private TourRecommendDao tourRecommendDao;
	
	@Override
	public List<TourRecommendVO> getTourRecommendList() throws Exception {
		return tourRecommendDao.getTourRecommendList();
	}

	@Override
	public List<TourVO> getTourRecommendFrontList() throws Exception {
		// TODO Auto-generated method stub
		return tourRecommendDao.getTourRecommendFrontList();
	}

	@Override
	public List<TourVO> getTourRecommendBest() throws Exception {
		// TODO Auto-generated method stub
		return tourRecommendDao.getTourRecommendBest();
	}

	
}
