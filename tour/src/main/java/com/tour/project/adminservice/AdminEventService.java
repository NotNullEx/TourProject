package com.tour.project.adminservice;

import java.util.List;
import java.util.Map;

import com.tour.project.adminvo.EventVO;

public interface AdminEventService {
	
	public int create(Map<String, Object> map);
	public List<EventVO> list(/* Map<String, Object> map */);
}
