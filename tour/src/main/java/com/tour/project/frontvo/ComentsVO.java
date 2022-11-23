package com.tour.project.frontvo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ComentsVO {
	//게시판 시퀀스
	private int board_seq;
	//댓글 내용
	private String coment_contents;
	//댓글 좋아요
	private int coment_like;
	//댓글 등록일
	private String coment_reg_date;
	//댓글 시퀀스
	private int coment_seq;
	//댓글 업데이트 날짜
	private String coment_upd_date;
	//유저 이메일
	private String mem_email;
	//유저 시퀀스
	private int mem_seq;
	//유저 이름
	private String mem_name;
}