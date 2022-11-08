package com.tour.project.admindao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.adminvo.EventVO;

@Repository
public class AdminEventDao {
	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public int create(EventVO vo) {
		return sqltemplate.insert("event.create", vo);
		
	}
	
	public List<EventVO> listAll() {
		List<EventVO> result = new ArrayList<EventVO>();
		result = sqltemplate.selectList("event.listAll");
		return result;
	}
	
	public int getTotal() {
		return sqltemplate.selectOne("event.getTotal");
	}
}
