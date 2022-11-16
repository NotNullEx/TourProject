package com.tour.project.adminvo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NotificationVO {
	private int noti_seq;
	private String admin_name;
	private String noti_title;
	private String noti_contents;
	private String noti_reg_date;
	private String noti_upd_date;
	private String noti_del_date;
	private int noti_status;
}
