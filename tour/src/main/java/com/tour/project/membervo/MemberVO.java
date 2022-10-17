package com.tour.project.membervo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MemberVO {
	private String mem_email;
	private String mem_name;
	private String mem_password;
	private String mem_phone_num;
	private String mem_regist_day;
	private int mem_seq;
	private int mem_status;
	private String mem_upd_date;
}
