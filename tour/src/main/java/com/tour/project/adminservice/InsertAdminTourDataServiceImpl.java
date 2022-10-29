package com.tour.project.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admindao.InsertAdminTourDataDAO;
import com.tour.project.adminvo.TourVO;

@Service
public class InsertAdminTourDataServiceImpl implements InsertAdminTourDataService{

	@Autowired
	private InsertAdminTourDataDAO dao;

	@Override
	public int tourInsert(TourVO vo) {
		int result = -1;
		result = dao.tourInsert(vo);
		return result;
	}
	
	

}
