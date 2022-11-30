package com.tour.project.admindao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.adminvo.EventVO;
import com.tour.project.common.vo.PageCriteriaVO;

@Repository
public class AdminEventDao {
	@Autowired 
	private SqlSessionTemplate sqltemplate;
	
	public int create(List<EventVO> lists) {
		return sqltemplate.insert("event.create", lists);
		
	}
	
	public int create(Map<String, Object> map) {
		return sqltemplate.insert("event.create", map);
		
	}
	
	public List<EventVO> listAll() {
		List<EventVO> result = new ArrayList<EventVO>();
		result = sqltemplate.selectList("event.listAll");
		return result;
	}
	
	public List<EventVO> listAll(PageCriteriaVO cri) {
		List<EventVO> result = new ArrayList<EventVO>();
		result = sqltemplate.selectList("event.pageListAll", cri);
		return result;
	}
	
	public int getTotal() {
		return sqltemplate.selectOne("event.getTotal");
	}
	
	public List<EventVO> listByCode(String code) {
		List<EventVO> result = new ArrayList<EventVO>();
		result = sqltemplate.selectList("event.listByCode", code);
		return result;
	}
	
	public int deleteAll() {
		int result = 0;
		result = sqltemplate.delete("event.deleteAll");
		return result;
	}
	
	public int deleteOne(String code) {
		int result = 0;
		result = sqltemplate.delete("event.deleteOne",code);
		return result;
	}
	
	public int reviseAll(Map<String, Object> map) {
		int result = 0;
		result = sqltemplate.update("event.reviseAll", map);
		return result;
	}
	
	public List<EventVO> getEventlist(PageCriteriaVO cri) {
		List<EventVO> list = sqltemplate.selectList("event.getEventlist",cri);
		return list;
	}
	
	public int getOngoingEventCount() {
		int cnt = sqltemplate.selectOne("event.getOngoingEventCount");
		
		return cnt;
	}
}
