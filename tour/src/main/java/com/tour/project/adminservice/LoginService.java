package com.tour.project.adminservice;

import java.util.Map;

public interface LoginService {

	public int login(Map<String, Object> map);
	
	public Map<String, Object> amindInfo(String id);
}
