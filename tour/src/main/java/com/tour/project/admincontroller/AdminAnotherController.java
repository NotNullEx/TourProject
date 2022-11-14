package com.tour.project.admincontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.tour.project.adminservice.AdminTourDataService;
import com.tour.project.adminvo.TourVO;
import com.tour.project.common.PageMaker;
import com.tour.project.common.vo.PageCriteriaVO;

@Controller
public class AdminAnotherController {

	
	@Autowired
	private AdminTourDataService service;
	
	@RequestMapping(value = {"/admin/tourList"})
	public ModelAndView about(PageCriteriaVO cri) {
		ModelAndView mav = new ModelAndView("/admin/tour_list");
		
		List<TourVO> lists = new ArrayList<TourVO>();
		List<TourVO> cntLists = new ArrayList<TourVO>();
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		lists = service.tourList(cri);
		cntLists = service.tourList();
		pageMaker.setTotalCount(cntLists.size());

		mav.addObject("sb", lists);
		mav.addObject("pageMaker", pageMaker);
		
		return mav;
	}
	
	@RequestMapping(value = {"/admin/adminDataInsert"})
	public ModelAndView adminDataInsert(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/admin_data_insert");
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = {"/admin/insTourData"})
	public Object insTourData(HashMap<String, Object> map, HttpServletRequest requset, HttpServletResponse resonse) {
		int result = 0;
		String tour_post_sn = requset.getParameter("tour_post_sn");
		String tour_post_sj = requset.getParameter("tour_post_sj");
		String tour_cmmn_fax = requset.getParameter("tour_cmmn_fax");
		String tour_subway_info = requset.getParameter("tour_subway_info");
		String tour_cmmn_hmpg_url = requset.getParameter("tour_cmmn_hmpg_url");
		String tour_cmmn_telno = requset.getParameter("tour_cmmn_telno");
		String tour_cmmn_bsnde = requset.getParameter("tour_cmmn_bsnde");
		String tour_bf_desc = requset.getParameter("tour_bf_desc");
		String tour_cmmn_rstde = requset.getParameter("tour_cmmn_rstde");
		String tour_cmmn_use_time = requset.getParameter("tour_cmmn_use_time");
		// 도로명 주소
		String new_address = requset.getParameter("sample4_postcode") + requset.getParameter("sample4_roadAddress")+requset.getParameter("sample4_detailAddress");
		String address = requset.getParameter("sample4_postcode")+requset.getParameter("sample4_jibunAddress")+requset.getParameter("sample4_detailAddress");
		
		map.put("tour_new_address", new_address);
		map.put("tour_address", address);
		map.put("tour_post_sn", tour_post_sn);
		map.put("tour_post_sj", tour_post_sj);
		map.put("tour_cmmn_fax", tour_cmmn_fax);
		map.put("tour_subway_info", tour_subway_info);
		map.put("tour_cmmn_hmpg_url", tour_cmmn_hmpg_url);
		map.put("tour_cmmn_telno", tour_cmmn_telno);
		map.put("tour_cmmn_bsnde", tour_cmmn_bsnde);
		map.put("tour_bf_desc", tour_bf_desc);
		map.put("tour_cmmn_rstde", tour_cmmn_rstde);
		map.put("tour_cmmn_use_time", tour_cmmn_use_time);
		result = service.tourInsert(map);
		if(result == 1) {
			return new Gson().toJson(result);
		}else {
			return result;
		}
	}
	
	@RequestMapping(value = {"/admin/dataUpdate"})
	public ModelAndView dataUpdate(HashMap<String, Object> map, HttpServletRequest requset, HttpServletResponse resonse) {
		ModelAndView mav = new ModelAndView("/admin/dataupdate");
		String tour_seq = requset.getParameter("tour_seq");
		List<TourVO> lists = new ArrayList<TourVO>();
		lists = service.tourOneList(tour_seq);
		String[] result = lists.get(0).getTour_address().split(" ");
		String address = result[2];

		mav.addObject("sb",lists);
		mav.addObject("address",address);
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = {"/admin/updateTourData"})
	public Object updateTourData(HashMap<String, Object> map, HttpServletRequest requset, HttpServletResponse resonse) {
		String tour_seq = requset.getParameter("tour_seq");
		String tour_cmmn_fax = requset.getParameter("tour_cmmn_fax");
		String tour_subway_info = requset.getParameter("tour_subway_info");
		String tour_cmmn_hmpg_url = requset.getParameter("tour_cmmn_hmpg_url");
		String tour_cmmn_telno = requset.getParameter("tour_cmmn_telno");
		String tour_cmmn_bsnde = requset.getParameter("tour_cmmn_bsnde");
		String tour_bf_desc = requset.getParameter("tour_bf_desc");
		String tour_cmmn_rstde = requset.getParameter("tour_cmmn_rstde");
		String tour_cmmn_use_time = requset.getParameter("tour_cmmn_use_time");
		// 도로명 주소
		String new_address = requset.getParameter("sample4_postcode") + requset.getParameter("sample4_roadAddress")+requset.getParameter("sample4_detailAddress");
		String address = requset.getParameter("sample4_postcode")+requset.getParameter("sample4_jibunAddress")+requset.getParameter("sample4_detailAddress");
		
		map.put("tour_seq", tour_seq);
		map.put("tour_new_address", new_address);
		map.put("tour_address", address);
		map.put("tour_cmmn_fax", tour_cmmn_fax);
		map.put("tour_subway_info", tour_subway_info);
		map.put("tour_cmmn_hmpg_url", tour_cmmn_hmpg_url);
		map.put("tour_cmmn_telno", tour_cmmn_telno);
		map.put("tour_cmmn_bsnde", tour_cmmn_bsnde);
		map.put("tour_bf_desc", tour_bf_desc);
		map.put("tour_cmmn_rstde", tour_cmmn_rstde);
		map.put("tour_cmmn_use_time", tour_cmmn_use_time);
		
		int result = -1;
		result = service.tourUpdateDate(map);
		
		if(result == 1) {
			return new Gson().toJson(result);
		}else {
			return new Gson().toJson(result);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = {"/admin/dataDelete"})
	public Object dataDelete(HttpServletRequest requset, HttpServletResponse resonse) {
		String tour_seq = requset.getParameter("tour_seq");
		
		int result = -1;
		result = service.tourDeleteDate(tour_seq);
		
		if(result == 1) {
			return new Gson().toJson(result);
		}else {
			return new Gson().toJson(result);
		}
	}
	
	@RequestMapping(value = {"/admin/faq"})
	public ModelAndView faq(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/faq");
		return mav;
	}
	
	@RequestMapping(value = {"/admin/index"})
	public ModelAndView index(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/index");
		return mav;
	}
	
	@RequestMapping(value = {"/admin/portfolioitem"})
	public ModelAndView portfolioitem(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/portfolioitem");
		return mav;
	}
	
	@RequestMapping(value = {"/admin/portfolioOverview"})
	public ModelAndView portfolioOverview(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/portfolioOverview");
		return mav;
	}
	
	@RequestMapping(value = {"/admin/pricing"})
	public ModelAndView pricing(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/pricing");
		return mav;
	}
}
