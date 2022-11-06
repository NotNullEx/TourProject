package com.tour.project.restaurantvo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RestaurantVO {
	// 음식점 코드
	private int res_code;
	// 관광지 고유번호
	private int tour_post_sn;
	// 관광지 주소
	private String tour_address;
	// 음식점 전화번호
	private String res_telnum;
	// 음식점 주소(구)
	private String res_adress_area;
	// 음식점 상세주소
	private String res_adress;
	// 음식점 오픈시간
	private String res_open_time;
	// 음식점 휴일
	private String res_rest_day;
	// 음식점 사진
	private String res_image;
	// 음식점 종류 : 0-한식 1-중식 2-일식 3-양식 
	private int res_kind;
	// 음식점 상세설명
	private String res_desc;
	// 음식점 이름
	private String res_name;
	// 음식점 등록일
	private String res_reg_date;
	// 고객 시퀀스
	private int mem_seq;
	// 음식점 업데이트 날짜
	private String res_upd_date;
}
	