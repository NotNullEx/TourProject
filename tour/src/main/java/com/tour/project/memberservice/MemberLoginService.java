package com.tour.project.memberservice;

import java.util.Map;

public interface MemberLoginService {
	public int login(Map<String, Object> map);
	
	public Map<String, Object> memberInfo(String id);
}
