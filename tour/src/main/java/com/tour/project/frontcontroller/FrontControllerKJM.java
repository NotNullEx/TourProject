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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tour.project.adminservice.AdminBoardService;
import com.tour.project.adminservice.AdminEventService;
import com.tour.project.adminservice.AdminRestaurantService;
import com.tour.project.adminvo.BoardVO;
import com.tour.project.adminvo.EventVO;
import com.tour.project.adminvo.RestaurantVO;
import com.tour.project.common.ResultSendToClient;
import com.tour.project.frontservice.FrontBoardService;

@Controller
public class FrontControllerKJM {
	
	@Autowired
	private AdminRestaurantService infoService;
	
	@Autowired
	private AdminEventService AES;
	
	@Autowired
	private FrontBoardService FBS;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	@RequestMapping(value = { "/" })
//	public ModelAndView frontHome() throws Exception {
//		ModelAndView models = new ModelAndView("/front/home");
//		try {
//
//			String key = "4f645452506b6a6d38307a53726f48";
//			StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /* URL */
//			urlBuilder.append("/" + URLEncoder.encode(key, "UTF-8")); /* 인증키 (sample사용시에는 호출시 제한됩니다.) */
//			urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /* 요청파일타입 (xml,xmlf,xls,json) */
//			urlBuilder.append("/" + URLEncoder.encode("SebcTourStreetKor", "UTF-8")); /* 서비스명 (대소문자 구분 필수입니다.) */
//
//			// 즉, 페이지라고 생각하면됩니다 1부터 5까지 출력
//			urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8")); /* 요청시작위치 (sample인증키 사용시 5이내 숫자) */
//			urlBuilder.append("/" + URLEncoder.encode("5", "UTF-8")); /* 요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨) */
//
////				urlBuilder.append("/" + URLEncoder.encode("NM_DP","UTF-8"));
//			// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
//
//			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
//			Date now = new Date();
//			String nowTime2 = sdf2.format(now);
//
//			// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
//			// urlBuilder.append("/" + URLEncoder.encode(nowTime2,"UTF-8")); /* 서비스별 추가
//			// 요청인자들*/
//
//			URL url = new URL(urlBuilder.toString());
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			conn.setRequestProperty("Content-type", "application/json");
//			System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다. */
//			BufferedReader rd;
//			// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
//			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//			} else {
//				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
//			}
//			ObjectMapper mapper = new ObjectMapper();
//			JsonNode rootNode = mapper.readTree(rd);
//			System.out.println(rootNode.toString());
//			Iterator<JsonNode> it = rootNode.path("SebcTourStreetKor").path("row").elements();
//			String info = null;
//			List<String> lists = new ArrayList<String>();
//			while (it.hasNext()) {
//				JsonNode node = it.next();
//				System.out.println("test: " + node.path("NM_DP"));
//				info = node.path("NM_DP").toString();
//				lists.add(info);
//			}
//			/* info.replaceAll("\\\"", "") */ //쌍따옴표 제거 
//			rd.close();
//			conn.disconnect();	
//			models.addObject("sb", lists);
//		} catch (Exception e) {
//			e.getStackTrace();
//		}
//		return models;
//	}
//
//	@RequestMapping(value = { "/front/bloghome" })
//	public ModelAndView blogHome(Locale locale, Model model) {
//		ModelAndView models = new ModelAndView("/front/bloghome");
//		try {
//
//			String key = "4f645452506b6a6d38307a53726f48";
//			StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /* URL */
//			urlBuilder.append("/" + URLEncoder.encode(key, "UTF-8")); /* 인증키 (sample사용시에는 호출시 제한됩니다.) */
//			urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /* 요청파일타입 (xml,xmlf,xls,json) */
//			urlBuilder.append("/" + URLEncoder.encode("SebcKoreanRestaurantsKor", "UTF-8")); /* 서비스명 (대소문자 구분 필수입니다.) */
//			// TbVwAttractions SebcKoreanRestaurantsKor
//
//			// 즉, 페이지라고 생각하면됩니다 1부터 5까지 출력
//			urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8")); /* 요청시작위치 (sample인증키 사용시 5이내 숫자) */
//			urlBuilder.append("/" + URLEncoder.encode("5", "UTF-8")); /* 요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨) */
//
////			urlBuilder.append("/" + URLEncoder.encode("NM_DP","UTF-8"));
//			// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
//
//			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
//			Date now = new Date();
//			String nowTime2 = sdf2.format(now);
//
//			// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
//			// urlBuilder.append("/" + URLEncoder.encode(nowTime2,"UTF-8")); /* 서비스별 추가
//			// 요청인자들*/
//
//			URL url = new URL(urlBuilder.toString());
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			conn.setRequestProperty("Content-type", "application/json");
//			System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다. */
//			BufferedReader rd;
//			// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
//			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//			} else {
//				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
//			}
//
//			ObjectMapper mapper = new ObjectMapper();
//			JsonNode rootNode = mapper.readTree(rd);
//			System.out.println(rootNode.toString());
////		      System.out.println(rootNode.path("LOCALDATA_031101").path("row"));
//			Iterator<JsonNode> it = rootNode.path("SebcKoreanRestaurantsKor").path("row").elements();
//			String info = null;
//			List<String> lists = new ArrayList<String>();
//			while (it.hasNext()) {
//				JsonNode node = it.next();
//				System.out.println(node.path("NM_DP"));
//				info = node.path("NM_DP").toString();
//				lists.add(info);
//			}
//			rd.close();
//			conn.disconnect();
//			models.addObject("lists", lists);
//		} catch (Exception e) {
//			e.getStackTrace();
//		}
//
//		return models;
//	}
	/*
	 * @RequestMapping(value = {"/front"}) public ModelAndView about(Locale locale,
	 * Model model) { ModelAndView mav = new ModelAndView("/front/about"); return
	 * mav; }
	 */

	
	@RequestMapping(value = {"/front/editBoard"})
	public ModelAndView editBoard(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/front/editBoard");
		List<BoardVO> bv = new ArrayList<BoardVO>();
		String seq = request.getParameter("board_seq");
		bv = FBS.listBySeq(seq);
		mav.addObject("list", bv);
		return mav;
		
	}
	
	@RequestMapping(value = {"/front/editBoardOK"})
	public void editBoardOK(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {
		int isCreated = FBS.edit(map);
		if(isCreated == 1) {
			System.out.println("success");
			ResultSendToClient.onlyResultTo(response, isCreated);
		}else {
			System.out.println("faile");
		}
	}
	
	
	@RequestMapping(value = {"/front/blogPost_detail"})
	public ModelAndView blogPostDetail(Locale locale, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/front/blogpost_detail");
		List<BoardVO> bv = new ArrayList<BoardVO>();
		String seq = request.getParameter("board_seq");
		bv = FBS.listBySeq(seq);
		mav.addObject("list", bv);
		return mav;
	}

	
	@RequestMapping(value = {"/front/blogPost"})
	public ModelAndView blogPost(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/front/blogpost");
		List<BoardVO> bv = new ArrayList<BoardVO>();
		bv = FBS.listAll();
		mav.addObject("list", bv);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = { "/front/deleteOneBoard" })
	public Object deleteOneRestaurant(@RequestParam String seq) throws Exception {
		int result = 0;
		result = (int) FBS.deleteOne(seq);
		return new Gson().toJson(result);
	}
	
	@RequestMapping(value = {"/front/createBoardOK"})
	public void createBoardOK(@RequestParam Map<String, Object> map, HttpServletResponse response) {
		int isCreated =  FBS.create(map);
		if(isCreated ==1) {
			System.out.println("success");
			ResultSendToClient.onlyResultTo(response, isCreated);
		}
		else {
			System.out.println("faile");
		}
	}
	
	
	@RequestMapping(value = {"/front/blog"})
	public ModelAndView blog(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/front/bloghome");	
		List<RestaurantVO> resVO = new ArrayList<RestaurantVO>();
		resVO = infoService.listAll();
		mav.addObject("data",resVO);
		return mav;
	}	
	
	
	@RequestMapping(value = {"/front/contact"})
	public ModelAndView contact() {
		ModelAndView mav = new ModelAndView("/front/contact");
		return mav;
	}
	
	
	@RequestMapping(value = {"/front/faq"})
	public ModelAndView faq(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/front/faq");
		return mav;
	}
	
	@RequestMapping(value = {"/front/index"})
	public ModelAndView index(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/front/index");
		return mav;
	}
	
	@RequestMapping(value = {"/front/portfolioitem"})
	public ModelAndView portfolioitem(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/front/portfolioitem");
		return mav;
	}
	
	@RequestMapping(value = {"/front/portfolioOverview"})
	public ModelAndView portfolioOverview(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/front/portfolioOverview");
		return mav;
	}
	
	@RequestMapping(value = {"/front/pricing"})
	public ModelAndView pricing(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/front/pricing");
		return mav;
	}
	
	
	

}
