package com.tour.project.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admindao.AdminTourDataDAO;
import com.tour.project.adminvo.TourVO;

@Service
public class AdminTourDataServiceImpl implements AdminTourDataService{

	@Autowired
	private AdminTourDataDAO dao;

	@Override
	public int tourInsert(TourVO vo) {
		int result = -1;
		result = dao.tourInsert(vo);
		return result;
	}

	@Override
	public List<TourVO> tourList() {
		List<TourVO> list = dao.tourList();
		return list;
	}
	
	

}
