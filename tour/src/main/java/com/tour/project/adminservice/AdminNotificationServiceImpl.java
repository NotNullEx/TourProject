package com.tour.project.adminservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admindao.AdminNotificationDao;
import com.tour.project.adminvo.NotificationVO;
import com.tour.project.common.vo.PageCriteriaVO;
@Service
public class AdminNotificationServiceImpl implements AdminNotificationService {
	@Autowired
	private AdminNotificationDao dao;
	
	@Override
	public int create(Map<String, Object> map) {
		return dao.create(map);
	}

	@Override
	public List<NotificationVO> pagingNotiList(PageCriteriaVO cri) throws Exception{
		return dao.pagingNotiList(cri);
	}

	@Override
	public NotificationVO getNotiDetailList(String seq) throws Exception {
		return dao.getNotiDetailList(seq);
	}

	@Override
	public int notiUpdate(Map<String, Object> map) throws Exception {
		return dao.notiUpdate(map);
	}

	@Override
	public int setNotiHidden(Map<String, Object> map) throws Exception {
		return dao.setNotiHidden(map);
	}

	@Override
	public int getTotal() throws Exception {
		return dao.getTotal();
	}

}
