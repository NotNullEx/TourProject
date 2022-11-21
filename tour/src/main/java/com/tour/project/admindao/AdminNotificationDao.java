package com.tour.project.admindao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tour.project.adminvo.NotificationVO;
import com.tour.project.common.vo.PageCriteriaVO;

@Repository
public class AdminNotificationDao {
	@Autowired
	SqlSessionTemplate sqltemplate;
	
	
	public int create(Map<String, Object> map) {
		return sqltemplate.insert("notification.create", map);
		
	}
	
	public List<NotificationVO> pagingNotiList(PageCriteriaVO cri) throws Exception{
		return sqltemplate.selectList("notification.pagingNotiList", cri);
	}
	
	public NotificationVO getNotiDetailList(String seq) throws Exception {
		return sqltemplate.selectOne("notification.getNotiDetail", seq);
	}
	
	public int notiUpdate(Map<String, Object> map) throws Exception {
		return sqltemplate.update("notification.notiUpdate", map);
	}
	
	public int setNotiHidden(Map<String, Object> map) throws Exception {
		return sqltemplate.update("notification.setNotiHidden", map);
	}
	
	public int getTotal() throws Exception {
		return sqltemplate.selectOne("notification.getTotal");
	}
	
	public List<NotificationVO> myNotiInfo(HashMap<String, Object> map) throws Exception{
		return sqltemplate.selectList("notification.myNotiInfoList",map);
	}
	
	public int getmyNotiTotal(String seq) throws Exception {
		return sqltemplate.selectOne("notification.getmyNotiTotal",seq);
	}
}
