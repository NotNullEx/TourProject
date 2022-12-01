package com.tour.project.frontcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.tour.project.adminservice.AdminTourDataService;
import com.tour.project.adminvo.TourVO;
import com.tour.project.common.PageMaker;
import com.tour.project.common.StringUtil;
import com.tour.project.common.UtilClass;
import com.tour.project.common.vo.PageCriteriaVO;
import com.tour.project.frontservice.FrontFavoritesService;
import com.tour.project.frontservice.MemberLoginService;
import com.tour.project.frontservice.TourRecommendService;
import com.tour.project.frontvo.MemberVO;
import com.tour.project.frontvo.TourRecommendVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FrontController {

	@Autowired
	private AdminTourDataService service;
	
	@Autowired
	private FrontFavoritesService frontFavoritesService;
	
	@Autowired
	private TourRecommendService tourRecommendService;
	
	@Autowired
	private MemberLoginService memberLoginService;
	

	@RequestMapping(value = { "/" })
	public ModelAndView home(HttpServletRequest request) throws Exception {
		ModelAndView models = new ModelAndView("/front/home");
		
		String memberId = (String) request.getSession().getAttribute("MEMBER_ID");
		if (!StringUtil.isEmpty(memberId)) {
			MemberVO memberInfo = memberLoginService.memberInfo(memberId);
			if (memberInfo != null) models.addObject("memberInfo", memberInfo);
		}
		TourVO tourInfo  = tourRecommendService.getTourRecommendBest().get(0);
		List<TourVO> list = tourRecommendService.getTourRecommendFrontList();

		models.addObject("tourInfo", tourInfo);
		models.addObject("list",list);
		return models;
	}
	
	@RequestMapping(value = { "/front/tourDetail" })
	public ModelAndView tourDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView models = new ModelAndView("/front/tourdetail");
		String tour_seq = request.getParameter("tour_seq");
		List<TourVO> lists = new ArrayList<TourVO>();
		lists = service.tourOneList(tour_seq);
		if (!StringUtil.isEmpty(lists.get(0).getTour_address())) {
			String[] result = lists.get(0).getTour_address().split(" ");
			String address = result[2];
			models.addObject("address", address);
		}
		
		if (!StringUtil.isEmpty(request.getSession().getAttribute("FRONT_US_SEQ"))) {
			int member_seq = (Integer) request.getSession().getAttribute("FRONT_US_SEQ");
			String mem_seq = Integer.toString(member_seq);
	
			models.addObject("mem_seq", mem_seq);
		}
		models.addObject("tour_seq", tour_seq);
		models.addObject("sb", lists);
		
		return models;

	}
	
	@RequestMapping(value = { "/front/tourList" })
	public ModelAndView tourList(PageCriteriaVO cri, HttpServletRequest request) throws Exception {
		ModelAndView models = new ModelAndView("/front/tourList");
		
		cri.setPerPageNum(9);
		List<TourVO> lists = service.tourList(cri);
		List<TourRecommendVO> tourRecommendList = tourRecommendService.getTourRecommendList();
		if (!StringUtil.isEmpty(request.getSession().getAttribute("FRONT_US_SEQ")))  {
			int member_seq = (Integer) request.getSession().getAttribute("FRONT_US_SEQ");
			List<TourVO> membetFavoriteLIst = frontFavoritesService.tourListByFavorites(member_seq);
			
			Map<Object,Object> wishMap = new HashMap<Object, Object>();
			
			for (TourVO favorite : membetFavoriteLIst) 
			{
				wishMap.put(favorite.getTour_seq(), favorite.getTour_seq());
			}
			
			for (TourVO tour : lists) {
				if (wishMap.containsKey(tour.getTour_seq())) {
					tour.setWishflag("Y");
				}
			}
		}
		
		int total =0;
		PageMaker pageMaker = new PageMaker();
		String device = UtilClass.isDevice(request);
		if ("MOBILE".equals(device)) {
			pageMaker.setDisplayPageNum(5);
		}
		pageMaker.setCri(cri);
		total = service.getToatal();
		pageMaker.setTotalCount(total);
		if(!StringUtil.isEmpty(lists)) {
			models.addObject("list", lists);
		}
		if (!StringUtil.isEmpty(tourRecommendList)) {
			models.addObject("tourRecommendList", tourRecommendList);
		}
		models.addObject("curPage",cri.getPage());
		models.addObject("totalCount", total);
		models.addObject("pageMaker", pageMaker);
		
		return models;
	}
	
	@RequestMapping(value = {"/front/createBoard"})
	public ModelAndView create(HttpServletRequest request,  HttpServletResponse response) {
		
		String user_id = (String) request.getSession().getAttribute("MEMBER_ID");
		
		if(user_id == null || "".equals(user_id)) {
			ModelAndView models = new ModelAndView("/front/member_login");
			return models;
		} else {
			return new ModelAndView("front/createBoard");
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = { "/front/tourFavorites" })
	public String tourFavorites(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int result = 0;
		String res = null;
		try {
			String mem_seq = request.getParameter("mem_seq");
			String tour_seq = request.getParameter("tour_seq");
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("tour_seq", tour_seq);
			map.put("mem_seq", mem_seq);
			result = frontFavoritesService.create(map);
			if(result != 0) res = Integer.toString(result);
			return new Gson().toJson(res);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
}