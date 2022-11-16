package com.tour.project.adminservice;

import java.util.List;
import java.util.Map;

import com.tour.project.adminvo.NotificationVO;

public interface AdminNotificationService {
	public int create(Map<String, Object> map);
	public List<NotificationVO> getNotiList() throws Exception;
	public NotificationVO getNotiDetailList(String seq) throws Exception;
}