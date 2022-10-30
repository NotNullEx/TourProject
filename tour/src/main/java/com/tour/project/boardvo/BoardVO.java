package com.tour.project.boardvo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BoardVO {
	private int board_seq;
	private int mem_seq;
	private String board_title;
	private String board_contents;
	private int board_status;
	private String board_reg_date;
	private String board_upd_date;
}
