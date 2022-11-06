package com.tour.project.boardvo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BoardVO {
	// 게시판 시퀀스
	private int board_seq;
	// 고객 시퀀스
	private int mem_seq;
	// 게시판 제목
	private String board_title;
	// 게시판 내용
	private String board_contents;
	// 게시판 종류 : 0-자유 1-질문 2-답변 3-숙박후기 4-음식점후기 5-축제후기
	private int board_status;
	// 게시판 등록일
	private String board_reg_date;
	// 게시판 업데이트 날짜
	private String board_upd_date;
}
