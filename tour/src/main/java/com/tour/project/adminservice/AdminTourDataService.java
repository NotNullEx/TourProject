package com.tour.project.adminservice;

import java.util.HashMap;
import java.util.List;

import com.tour.project.adminvo.TourVO;
import com.tour.project.common.vo.PageCriteriaVO;

public interface AdminTourDataService {

	public int tourInsert(TourVO vo);
	public int tourInsert(HashMap<String, Object> map);
	public List<TourVO> tourList(PageCriteriaVO cri);
	public List<TourVO> tourList();
	public List<TourVO> tourOneList(String tour_seq);
	public int tourUpdateDate(HashMap<String, Object> map);
	public int tourDeleteDate(String seq);
}
