package com.tour.project.adminservice;

import java.util.List;

import com.tour.project.adminvo.TourVO;

public interface AdminTourDataService {

	public int tourInsert(TourVO vo);
	
	public List<TourVO> tourList();
}
