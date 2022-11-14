package com.tour.project.adminservice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admindao.AdminTourDataDAO;
import com.tour.project.adminvo.TourVO;
import com.tour.project.common.vo.PageCriteriaVO;

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
	
	public List<TourVO> tourList(PageCriteriaVO cri) {
		List<TourVO> list = dao.tourList(cri);
		return list;
	}

	@Override
	public List<TourVO> tourOneList(String tour_seq) {
		List<TourVO> list = dao.tourOneList(tour_seq);
		return list;
	}

	@Override
	public int tourInsert(HashMap<String, Object> map) {
		int result = -1;
		result = dao.tourInsert(map);
		return result;
	}

	@Override
	public int tourUpdateDate(HashMap<String, Object> map) {
		int result = -1;
		result = dao.tourUpdateData(map);
		
		return result;
	}

	@Override
	public int tourDeleteDate(String seq) {
		int result = -1;
		result = dao.tourDeleteDate(seq);
		
		return result;
	}

}
