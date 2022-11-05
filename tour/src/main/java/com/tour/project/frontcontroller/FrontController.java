package com.tour.project.frontcontroller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tour.project.admincontroller.AdminController;
import com.tour.project.adminservice.AdminTourDataService;
import com.tour.project.adminvo.TourVO;
import com.tour.project.restaurantservice.RestaurantInfoService;
import com.tour.project.restaurantvo.RestaurantVO;

@Controller
public class FrontController {
	

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	
	@Autowired
	private AdminTourDataService service;

	private static final Logger log = LoggerFactory.getLogger(FrontController.class);

	@RequestMapping(value = { "/" })
	public ModelAndView dataInsert() throws Exception {
		ModelAndView models = new ModelAndView("/front/home");
		List<TourVO> lists = new ArrayList<TourVO>();
		lists = service.tourList();

		models.addObject("sb", lists);
		return models;
	}
	
	@RequestMapping(value = { "/front/tourDetail" })
	public ModelAndView tourDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView models = new ModelAndView("/front/tourdetail");
		String tour_seq = request.getParameter("tour_seq");
		List<TourVO> lists = new ArrayList<TourVO>();
		lists = service.tourOneList(tour_seq);
		String[] result = lists.get(0).getTour_address().split(" ");
		String address = result[2];

		models.addObject("sb",lists);
		models.addObject("address",address);
		return models;
	}
	
	@RequestMapping(value = { "/front/tourList" })
	public ModelAndView tourList() throws Exception {
		ModelAndView models = new ModelAndView("/front/tourList");
		List<TourVO> lists = new ArrayList<TourVO>();
		lists = service.tourList();

		models.addObject("sb", lists);
		return models;
	}
	
	@RequestMapping(value = {"/front/createBoard"}, method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request,  HttpServletResponse response) {
		
		String user_id = (String) request.getSession().getAttribute("MEMBER_ID");
		
		if(user_id == null || "".equals(user_id)) {
			ModelAndView models = new ModelAndView("/front/member_login");
			return models;
		} else {
			return new ModelAndView("front/createBoard");
		}
		
	}
}