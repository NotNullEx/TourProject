package com.tour.project.frontservice;

import java.util.Map;

import com.tour.project.frontvo.MemberVO;

public interface MemberLoginService {
	public int login(Map<String, Object> map);
	
	public MemberVO memberInfo(String id);
	
	public int memDel(String id) throws Exception;
}
