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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tour.project.admin_eventservice.CreateEventService;
import com.tour.project.admin_eventservice.EventListService;
import com.tour.project.admin_eventvo.EventVO;
import com.tour.project.restaurantservice.RestaurantInfoService;
import com.tour.project.restaurantvo.RestaurantVO;

@Controller
public class FrontControllerKJM {
	
	@Autowired
	private RestaurantInfoService infoService;
	
	@Autowired
	private EventListService ELS;
	
	@Autowired
	private CreateEventService CES;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/" })
	public ModelAndView frontHome() throws Exception {
		ModelAndView models = new ModelAndView("/front/home");
		try {

			String key = "4f645452506b6a6d38307a53726f48";
			StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /* URL */
			urlBuilder.append("/" + URLEncoder.encode(key, "UTF-8")); /* 인증키 (sample사용시에는 호출시 제한됩니다.) */
			urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /* 요청파일타입 (xml,xmlf,xls,json) */
			urlBuilder.append("/" + URLEncoder.encode("SebcTourStreetKor", "UTF-8")); /* 서비스명 (대소문자 구분 필수입니다.) */

			// 즉, 페이지라고 생각하면됩니다 1부터 5까지 출력
			urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8")); /* 요청시작위치 (sample인증키 사용시 5이내 숫자) */
			urlBuilder.append("/" + URLEncoder.encode("5", "UTF-8")); /* 요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨) */

//				urlBuilder.append("/" + URLEncoder.encode("NM_DP","UTF-8"));
			// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			Date now = new Date();
			String nowTime2 = sdf2.format(now);

			// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
			// urlBuilder.append("/" + URLEncoder.encode(nowTime2,"UTF-8")); /* 서비스별 추가
			// 요청인자들*/

			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다. */
			BufferedReader rd;
			// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
			}
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(rd);
			System.out.println(rootNode.toString());
			Iterator<JsonNode> it = rootNode.path("SebcTourStreetKor").path("row").elements();
			String info = null;
			List<String> lists = new ArrayList<String>();
			while (it.hasNext()) {
				JsonNode node = it.next();
				System.out.println("test: " + node.path("NM_DP"));
				info = node.path("NM_DP").toString();
				lists.add(info);
			}
			/* info.replaceAll("\\\"", "") */ //쌍따옴표 제거 
			rd.close();
			conn.disconnect();	
			models.addObject("sb", lists);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return models;
	}
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
	@RequestMapping(value = {"/front"})
	public ModelAndView about(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/front/about");
		return mav;
	}
	
	@RequestMapping(value = {"/front/blog"})
	public ModelAndView blog(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/front/bloghome");	
		List<RestaurantVO> resVO = new ArrayList<RestaurantVO>();
		resVO = infoService.list();
		mav.addObject("data",resVO);
		return mav;
	}	
	
	@RequestMapping(value = {"/front/blogPost"})
	public ModelAndView blogPost(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/front/blogpost");
		List<EventVO> list = new ArrayList<EventVO>();
		list = ELS.list();
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping(value = {"/front/contact"})
	public ModelAndView contact() {
		ModelAndView mav = new ModelAndView("/front/contact");
		return mav;
	}
	
	@RequestMapping(value = {"/front/contact"}, method = RequestMethod.POST)
	public ModelAndView contact(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView("/front/home");
		CES.create(map);
		mav.addAllObjects(map);
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