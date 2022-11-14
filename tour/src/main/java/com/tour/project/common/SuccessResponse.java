package com.tour.project.common;

import lombok.Getter;

@Getter
public class SuccessResponse {

	int code;
	String message;
	Object obj;
	
	public SuccessResponse(int code, String message, Object obj) {
		this.code = code;
		this.message = message;
		this.obj = obj;
	}
	
	/*
	 * controller에서
	 * return new SuccessResponse(코드,메세지,리턴데이터);
	 */
	
	/**
	 * code : rest코드 입력 성공 : 200 HttpServlertResponse의 SC_OK사용해도 무관
	 * message : return message
	 * obj : return할 데이터
	 */
}
