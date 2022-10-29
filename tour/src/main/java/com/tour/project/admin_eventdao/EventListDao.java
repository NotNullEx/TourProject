package com.tour.project.admin_eventdao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.admin_eventvo.EventVO;

@Repository
public class EventListDao {
	@Autowired
	private SqlSessionTemplate sqltemplate;
	
	public List<EventVO> list(/* Map<String, Object> map */) {
		List<EventVO> result = new ArrayList<EventVO>();
		result = sqltemplate.selectList("event.list"/* , map */);
		return result;
	}
}
