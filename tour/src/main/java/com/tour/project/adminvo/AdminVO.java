package com.tour.project.adminvo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AdminVO {
	// 관리자 이메일
	private String admin_email;
	// 관리자 이름
	private String admin_name;
	// 관리자 비밀번호
	private String admin_password;
	// 관리자 전화번호
	private String admin_phone_num;
	// 관리자 등록일
	private String admin_regist_day;
	// 관리자 시퀀스
	private int admin_seq;
	// 관리자 상태
	private int admin_status;
}
