package com.tour.project.adminservice;

import java.util.List;
import java.util.Map;

import com.tour.project.adminvo.NotificationVO;
import com.tour.project.common.vo.PageCriteriaVO;

public interface AdminNotificationService {
	public int create(Map<String, Object> map);
	public List<NotificationVO> pagingNotiList(PageCriteriaVO cri) throws Exception;
	public NotificationVO getNotiDetailList(String seq) throws Exception;
	public int notiUpdate(Map<String, Object> map) throws Exception;
	public int setNotiHidden(Map<String, Object> map) throws Exception;
	public int getTotal() throws Exception;
}