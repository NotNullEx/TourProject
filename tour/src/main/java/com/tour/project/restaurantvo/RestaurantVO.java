package com.tour.project.restaurantvo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RestaurantVO {
	private int res_code;
	private int tour_seq;
	private int tour_post_sn;
	private int mem_seq;
	private String res_telnum;
	private String res_adress;
	private String res_open_time;
	private String res_rest_day;
	private String res_head_menu;
	private String res_image;
	private int res_kind;
	private String res_desc;
	private String res_name;
}
	