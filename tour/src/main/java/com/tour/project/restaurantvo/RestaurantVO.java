package com.tour.project.restaurantvo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RestaurantVO {
	// 
	private int res_code;
	private int tour_post_sn;
	private String tour_address;
	private String res_telnum;
	private String res_adress_area;
	private String res_adress;
	private String res_open_time;
	private String res_rest_day;
	private String res_image;
	private int res_kind;
	private String res_desc;
	private String res_name;
	private String res_reg_date;
	private int mem_seq;
}
	