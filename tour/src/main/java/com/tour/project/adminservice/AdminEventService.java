package com.tour.project.adminservice;

import java.util.List;
import java.util.Map;

import com.tour.project.adminvo.EventVO;
import com.tour.project.common.vo.PageCriteriaVO;

public interface AdminEventService {
	
	public int create(List<EventVO> lists);
	public int create(Map<String, Object> map);
	public List<EventVO> listAll(PageCriteriaVO cri);
	public List<EventVO> listAll();
	public int getTotal();
	public List<EventVO> listByCode(String code);
	public int deleteAll();
	public int deleteOne(String code);
	public int reviseAll(Map<String, Object> map);
	public List<EventVO> getEventlist(PageCriteriaVO cri) throws Exception;
	public int getOngoingEventCount() throws Exception;
}
