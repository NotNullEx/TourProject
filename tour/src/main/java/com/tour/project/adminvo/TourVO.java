package com.tour.project.adminvo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TourVO {
	
	// 관광 시퀀스
	private int tour_seq;
	// 팩스 번호
	private String tour_cmmn_fax;
	// 주소
	private String tour_address; 
	// 신주소
	private String tour_new_address;
	// 교통정보
	private String tour_subway_info;
	// 웹사이트
	private String tour_cmmn_hmpg_url;
	// 전화번호
	private String tour_cmmn_telno; 
	// 운영요일
	private String tour_cmmn_bsnde;
	// 장애인 편의시설
	private String tour_bf_desc; 
	// 휴무일
	private String tour_cmmn_rstde; 
	// 운영 시간
	private String tour_cmmn_use_time;
	// 상호명
	private String tour_post_sj;
	// 고유 번호
	private String tour_post_sn;

}
