package com.tour.project.admin_eventvo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EventVO {
	private String even_code;
	private int tour_seq;
	private int tour_post_sn;
	private int mem_seq;
	private String even_name;
	private String even_start_date;
	private String even_end_date;
	private String even_adress;
	private int even_status;
	private String even_desc;
}
