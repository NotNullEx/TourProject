package com.tour.project.adminvo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EventVO {
	// 이벤트 코드
	private int even_code;
	// 관광지 시퀀스
	private int tour_seq;
	// 고객 시퀀스
	private int mem_seq;
	// 이벤트 분류
	private String even_codename;
	// 이벤트 자치구
	private String even_guname;
	// 이벤트 공연/행사명
	private String even_title;
	// 이벤트 날짜/시간
	private String even_date;
	// 이벤트 장소
	private String even_place;
	// 이벤트 기관명
	private String even_org_name;
	// 이벤트 이용대상
	private String even_use_trgt;
	// 이벤트 이용요금
	private String even_use_fee;
	// 이벤트 출연자정보
	private String even_player;
	// 이벤트 프로그램소개
	private String even_program;
	// 이벤트 기타내용
	private String even_etc_desc;
	// 이벤트 홈페이지 주소
	private String even_org_link;
	// 이벤트 대표이미지
	private String even_main_img;
	// 이벤트 신청일
	private String even_rgst_date;
	// 이벤트 시민/기관
	private String even_ticket;
	// 이벤트 시작일
	private String even_strt_date;
	// 이벤트 종료일
	private String even_end_date;
	// 이벤트 테마분류
	private String even_theme_code;
}
