package com.tour.project.adminservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.project.admindao.AdminNotificationDao;
import com.tour.project.adminvo.NotificationVO;
@Service
public class AdminNotificationServiceImpl implements AdminNotificationService {
	@Autowired
	private AdminNotificationDao dao;
	
	@Override
	public int create(Map<String, Object> map) {
		return dao.create(map);
	}

	@Override
	public List<NotificationVO> getNotiList() throws Exception{
		return dao.getNotiList();
	}

	@Override
	public NotificationVO getNotiDetailList(String seq) throws Exception {
		return dao.getNotiDetailList(seq);
	}

}
