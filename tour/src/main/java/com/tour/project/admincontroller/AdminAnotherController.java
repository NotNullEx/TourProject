package com.tour.project.admincontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.tour.project.common.UtilClass;
import com.tour.project.common.vo.PageCriteriaVO;

@Controller
public class AdminAnotherController {

	private static final Logger log = LoggerFactory.getLogger(AdminAnotherController.class);
	
	@Autowired
	private AdminTourDataService service;
	
	@RequestMapping(value = {"/admin/tourList"})
	public ModelAndView about(PageCriteriaVO cri, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/admin/tour_list");
		
		List<TourVO> lists = new ArrayList<TourVO>();
		int total =0;
		PageMaker pageMaker = new PageMaker();
		String device = UtilClass.isDevice(request);
		if ("MOBILE".equals(device)) {
			pageMaker.setDisplayPageNum(5);
		}
		pageMaker.setCri(cri);
		lists = service.tourList(cri);
		total = service.getToatal();
		pageMaker.setTotalCount(total);		
		
		if(lists != null && lists.size() > 0) {
			mav.addObject("list", lists);
		}
		mav.addObject("curPage",cri.getPage());
		mav.addObject("totalCount", total);
		mav.addObject("pageMaker", pageMaker);
		
		return mav;
	}
	
	@RequestMapping(value = {"/admin/addImage"})
	public ModelAndView addImage(HttpServletRequest requset, HttpServletResponse resonse) throws Exception {
		ModelAndView mav = new ModelAndView("/admin/addimage");
		String seq = requset.getParameter("tour_seq");
		String tour_post_sj = requset.getParameter("tour_post_sj");
		mav.addObject("tour_seq",seq);
		mav.addObject("tour_post_sj",tour_post_sj);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = {"/admin/insUrl"})
	public Object insUrl(HttpServletRequest requset, HttpServletResponse resonse) throws Exception {
		String seq = requset.getParameter("tour_seq");
		String url = requset.getParameter("url");
		HashMap<String, String> map = new HashMap<String, String>();
		if(!url.contains(".jpg")) {
			url += ".jpg";
			map.put("url", url);
		} else {
			map.put("url", url);
		}
		map.put("tour_seq", seq);
		int result = -1;
		int chk = -1;
		try {
			chk = service.chkUrl(map);
			if(chk != 1){
				result = service.insUrl(map);
				return new Gson().toJson(result);
			}else {
				chk = 0;
				return new Gson().toJson(chk);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception();
		}
	}
	
	@RequestMapping(value = {"/admin/adminDataInsert"})
	public ModelAndView adminDataInsert(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/admin_data_insert");
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = {"/admin/insTourData"})
	public Object insTourData(HashMap<String, Object> map, HttpServletRequest requset, HttpServletResponse resonse) throws Exception {
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
	public ModelAndView dataUpdate(HashMap<String, Object> map, HttpServletRequest requset, HttpServletResponse resonse) throws Exception {
		ModelAndView mav = new ModelAndView("/admin/dataupdate");
		String tour_seq = requset.getParameter("tour_seq");
		List<TourVO> lists = new ArrayList<TourVO>();
		lists = service.tourOneList(tour_seq);
		for(int i = 0; i<lists.size(); i++) {
			if(lists.get(i).getTour_cmmn_fax() == null || "".equals(lists.get(i).getTour_cmmn_fax()))lists.get(i).setTour_cmmn_fax("-");
			if(lists.get(i).getTour_address() == null || "".equals(lists.get(i).getTour_address()))lists.get(i).setTour_address("-");
			if(lists.get(i).getTour_new_address() == null || "".equals(lists.get(i).getTour_new_address()))lists.get(i).setTour_new_address("-");
			if(lists.get(i).getTour_subway_info() == null || "".equals(lists.get(i).getTour_subway_info()))lists.get(i).setTour_subway_info("-");
			if(lists.get(i).getTour_cmmn_hmpg_url() == null || "".equals(lists.get(i).getTour_cmmn_hmpg_url()))lists.get(i).setTour_cmmn_hmpg_url("-");
			if(lists.get(i).getTour_cmmn_telno() == null || "".equals(lists.get(i).getTour_cmmn_telno()))lists.get(i).setTour_cmmn_telno("-");
			if(lists.get(i).getTour_cmmn_bsnde() == null || "".equals(lists.get(i).getTour_cmmn_bsnde()))lists.get(i).setTour_cmmn_bsnde("-");
			if(lists.get(i).getTour_bf_desc() == null || "".equals(lists.get(i).getTour_bf_desc()))lists.get(i).setTour_bf_desc("-");
			if(lists.get(i).getTour_cmmn_rstde() == null || "".equals(lists.get(i).getTour_cmmn_rstde()))lists.get(i).setTour_cmmn_rstde("-");
			if(lists.get(i).getTour_cmmn_use_time() == null || "".equals(lists.get(i).getTour_cmmn_use_time()))lists.get(i).setTour_cmmn_use_time("-");
			if(lists.get(i).getTour_post_sj() == null || "".equals(lists.get(i).getTour_post_sj()))lists.get(i).setTour_post_sj("-");
			if(lists.get(i).getTour_post_sn() == null || "".equals(lists.get(i).getTour_post_sn()))lists.get(i).setTour_post_sn("-");
			
		}
		String address = "";
		if(lists.get(0).getTour_address() != "-" || !"-".equals(lists.get(0).getTour_address())) {
			String[] result = lists.get(0).getTour_address().split(" ");
			address = result[2];
		}
		mav.addObject("sb",lists);
		mav.addObject("address",address);
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = {"/admin/updateTourData"})
	public Object updateTourData(HashMap<String, Object> map, HttpServletRequest requset, HttpServletResponse resonse) throws Exception {
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
	public Object dataDelete(HttpServletRequest requset, HttpServletResponse resonse) throws Exception {
		String tour_seq = requset.getParameter("tour_seq");
		
		int result = -1;
		result = service.tourDeleteDate(tour_seq);
		
		if(result == 1) {
			return new Gson().toJson(result);
		}else {
			return new Gson().toJson(result);
		}
	}

	@RequestMapping(value = {"/admin/pricing"})
	public ModelAndView pricing(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/pricing");
		return mav;
	}
}
