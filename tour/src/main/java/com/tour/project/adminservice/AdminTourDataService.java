package com.tour.project.adminservice;

import java.util.HashMap;
import java.util.List;

import com.tour.project.adminvo.TourVO;
import com.tour.project.common.vo.PageCriteriaVO;

public interface AdminTourDataService {

	public int tourInsert(TourVO vo) throws Exception;
	public int tourInsert(HashMap<String, Object> map)throws Exception;
	public List<TourVO> tourList(PageCriteriaVO cri)throws Exception;
	public List<TourVO> tourList()throws Exception;
	public int getToatal()throws Exception;
	public List<TourVO> tourOneList(String tour_seq)throws Exception;
	public int tourUpdateDate(HashMap<String, Object> map)throws Exception;
	public int tourDeleteDate(String seq)throws Exception;
	public int insUrl(HashMap<String, String> map) throws Exception;
	public int chkUrl(HashMap<String, String> map) throws Exception;
}
