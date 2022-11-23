package com.tour.project.adminvo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NotificationVO {
	//공지사항 시퀀스
	private int noti_seq;
	//관리자 이름
	private String admin_name;
	//공지사항 제목
	private String noti_title;
	//공지사항 내용
	private String noti_contents;
	//공지사항 등록일
	private String noti_reg_date;
	//공지사항 업데이트 날짜
	private String noti_upd_date;
	//공지사항 삭제 날짜
	private String noti_del_date;
	//공지사항 상태(0 = 비표시/ 1 = 표시)
	private int noti_status;
}
