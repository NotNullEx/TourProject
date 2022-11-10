package com.tour.project.adminservice;

import java.util.List;
import java.util.Map;

import com.tour.project.adminvo.EventVO;

public interface AdminEventService {
	
	public int create(EventVO vo);
	public int create(Map<String, Object> map);
	public List<EventVO> listAll();
	public int getTotal();
	public List<EventVO> listByCode(String code);
	public int deleteAll();
	public int deleteOne(String code);
	public int reviseAll(Map<String, Object> map);
}
