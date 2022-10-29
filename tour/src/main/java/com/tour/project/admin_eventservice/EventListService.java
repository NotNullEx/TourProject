package com.tour.project.admin_eventservice;

import java.util.List;
import java.util.Map;

import com.tour.project.admin_eventvo.EventVO;

public interface EventListService {
	public List<EventVO> list(/* Map<String, Object> map */);
}
