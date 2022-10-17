package com.tour.project.adminvo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AdminVO {
	private String admin_email;
	private String admin_name;
	private String admin_password;
	private String admin_phone_num;
	private String admin_regist_day;
	private int admin_seq;
	private int admin_status;
}
