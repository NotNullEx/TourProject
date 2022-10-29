package com.tour.project.admin_eventservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admin_eventdao.EventListDao;
import com.tour.project.admin_eventvo.EventVO;
@Service
public class EventListServiceImpl implements EventListService {
	@Autowired
	private EventListDao listdao;
	
	@Override
	public List<EventVO> list(/* Map<String, Object> map */) {
		return listdao.list(/* map */);
	}

}
