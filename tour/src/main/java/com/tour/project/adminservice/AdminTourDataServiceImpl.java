package com.tour.project.adminservice;

import java.util.ArrayList;
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
	public int tourInsert(TourVO vo) throws Exception{
		int result = -1;
		result = dao.tourInsert(vo);
		return result;
	}

	@Override  
	public int getToatal() throws Exception{
		int getTotal = dao.getToatal();
		return getTotal;
	}
	
	public List<TourVO> tourList(PageCriteriaVO cri) throws Exception{
		List<TourVO> list = dao.tourList(cri);
		return list;
	}

	@Override
	public List<TourVO> tourOneList(String tour_seq) throws Exception{
		List<TourVO> list = dao.tourOneList(tour_seq);
		return list;
	}

	@Override
	public int tourInsert(HashMap<String, Object> map) throws Exception{
		int result = -1;
		result = dao.tourInsert(map);
		return result;
	}

	@Override
	public int tourUpdateDate(HashMap<String, Object> map) throws Exception{
		int result = -1;
		result = dao.tourUpdateData(map);
		
		return result;
	}

	@Override
	public int tourDeleteDate(String seq) throws Exception{
		int result = -1;
		result = dao.tourDeleteDate(seq);
		
		return result;
	}

	@Override
	public int insUrl(HashMap<String, String> map) throws Exception {
		int result = -1;
		result = dao.insUrl(map);
		return result;
	}

	@Override
	public int chkUrl(HashMap<String, String> map) throws Exception {
		int result = 0;
		result = dao.chkUrl(map);
		if(result ==1) return result;
		else return result;
	}

	@Override
	public List<TourVO> getTourListAll() throws Exception {
	
		return dao.getTourListAll();
	}

	@Override
	public List<HashMap<String, Integer>> memTotal() throws Exception {
		List<HashMap<String, Integer>> list = new ArrayList<HashMap<String,Integer>>();
		list = dao.memTotal();
		return list;
	}

	@Override
	public HashMap<String, Integer> getContentsTotal() throws Exception {
		HashMap<String, Integer> list = new HashMap<String, Integer>();
		list = dao.getContentsTotal();
		return list;
	}
}
