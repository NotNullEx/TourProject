package com.tour.project.eventvo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EventVO {
	public String even_code;
	public int tour_seq;
	public int tour_post_sn;
	public int mem_seq;
	public String even_name;
	public String even_start_date;
	public String even_end_date;
	public String even_adress;
	public int even_status;
	public String even_desc;
}
