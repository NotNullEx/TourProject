package com.tour.project.admindao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.adminvo.NotificationVO;

@Repository
public class AdminNotificationDao {
	@Autowired
	SqlSessionTemplate sqltemplate;
	
	
	public int create(Map<String, Object> map) {
		return sqltemplate.insert("notification.create", map);
		
	}
	
	public List<NotificationVO> getNotiList() throws Exception{
		return sqltemplate.selectList("notification.list");
	}
	
	public NotificationVO getNotiDetailList(String seq) throws Exception {
		return sqltemplate.selectOne("notification.getNotiDetail", seq);
	}
}
