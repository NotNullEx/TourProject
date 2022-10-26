package com.tour.project.admincontroller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tour.project.adminvo.TourVO;
import com.tour.project.restaurantdao.RestaurantInfoDao;
import com.tour.project.restaurantservice.CreateRestaurantService;
import com.tour.project.restaurantservice.RestaurantInfoService;
import com.tour.project.restaurantvo.RestaurantVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {
	
	@Autowired
	private CreateRestaurantService service;
	
	@Autowired
	private RestaurantInfoService infoService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/" })
	public ModelAndView publicApi() throws Exception {
		ModelAndView models = new ModelAndView("/admin/home");
		try {

			String key = "4f645452506b6a6d38307a53726f48";
			StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /* URL */
			urlBuilder.append("/" + URLEncoder.encode(key, "UTF-8")); /* 인증키 (sample사용시에는 호출시 제한됩니다.) */
			urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /* 요청파일타입 (xml,xmlf,xls,json) */
			urlBuilder.append("/" + URLEncoder.encode("TbVwAttractions", "UTF-8")); /* 서비스명 (대소문자 구분 필수입니다.) */

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
			Iterator<JsonNode> it = rootNode.path("TbVwAttractions").path("row").elements();
			String info = null;
			List<String> lists = new ArrayList<String>();
			TourVO tourInfo = new TourVO();
			while (it.hasNext()) {
				JsonNode node = it.next();
				System.out.println("test: " + node.path("NM_DP"));
				info = node.path("NM_DP").toString();
				lists.add(info);
//				tourInfo.setADD_KOR(node.path("NAME_KOR").toString());
//				tourInfo.setLAW_SGG(node.path("ADD_KOR").toString());
//				tourInfo.setNAME_KOR(node.path("LAW_SGG").toString());
//				tourInfo.setWGS84_X(node.path("WGS84_X").toString());
//				tourInfo.setWGS84_Y(node.path("WGS84_Y").toString());
				
//				LOGGER.info("input Messege:" + tourInfo.getNAME_KOR() + ","+ tourInfo.getADD_KOR() 
//				+ ","+ tourInfo.getLAW_SGG() + ","+ tourInfo.getWGS84_X()+ ","+
//				tourInfo.getWGS84_Y());
				
				
			}
			

			rd.close();
			conn.disconnect();
			models.addObject("sb", lists);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return models;
	}

	@RequestMapping(value = {"/admin/test"})
	public ModelAndView blogHome(Locale locale, Model model) {
		ModelAndView models = new ModelAndView("/admin/test");
		try {

			String key = "4f645452506b6a6d38307a53726f48";
			StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /* URL */
			urlBuilder.append("/" + URLEncoder.encode(key, "UTF-8")); /* 인증키 (sample사용시에는 호출시 제한됩니다.) */
			urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /* 요청파일타입 (xml,xmlf,xls,json) */
			urlBuilder.append("/" + URLEncoder.encode("SebcKoreanRestaurantsKor", "UTF-8")); /* 서비스명 (대소문자 구분 필수입니다.) */
			// TbVwAttractions SebcKoreanRestaurantsKor

			// 즉, 페이지라고 생각하면됩니다 1부터 5까지 출력
			urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8")); /* 요청시작위치 (sample인증키 사용시 5이내 숫자) */
			urlBuilder.append("/" + URLEncoder.encode("5", "UTF-8")); /* 요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨) */

//			urlBuilder.append("/" + URLEncoder.encode("NM_DP","UTF-8"));
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
//		      System.out.println(rootNode.path("LOCALDATA_031101").path("row"));
			Iterator<JsonNode> it = rootNode.path("SebcKoreanRestaurantsKor").path("row").elements();
			String info = null;
			List<String> lists = new ArrayList<String>();
			while (it.hasNext()) {
				JsonNode node = it.next();
				System.out.println(node.path("NM_DP"));
				info = node.path("NM_DP").toString();
				lists.add(info);
				service.create(info);
			}
			rd.close();
			conn.disconnect();
			models.addObject("lists", lists);
		} catch (Exception e) {
			e.getStackTrace();
		}

		return models;
	}
	
	@RequestMapping(value = {"/admin/about"})
	public ModelAndView about(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/about");
		return mav;
	}
	
	@RequestMapping(value = {"/admin/blog"})
	public ModelAndView blog(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/bloghome");	
		List<RestaurantVO> resVO = new ArrayList<RestaurantVO>();
		int index = 1;
		resVO = infoService.list(index);
		mav.addObject("data",resVO);
		return mav;
	}
	
	@RequestMapping(value = {"/admin/blogPost"})
	public ModelAndView blogPost(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/blogpost");
		return mav;
	}
	
	@RequestMapping(value = {"/admin/contact"})
	public ModelAndView contact(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/contact");
		return mav;
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