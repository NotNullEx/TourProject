package com.tour.project.admincontroller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tour.project.adminservice.AdminEventService;
import com.tour.project.adminservice.AdminRestaurantService;
import com.tour.project.adminservice.AdminTourDataService;
import com.tour.project.adminvo.EventVO;
import com.tour.project.adminvo.RestaurantVO;
import com.tour.project.adminvo.TourVO;
import com.tour.project.common.ResultSendToClient;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminControllerBYS {
	
	@Autowired
	private AdminRestaurantService ARS;
	
	@Autowired
	private AdminEventService AES;
	
	@Autowired
	private AdminTourDataService ATDS;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminControllerBYS.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

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
//				info = info.replaceAll("\\\"", "");
				lists.add(info);
//				service.create(info);
			}
			rd.close();
			conn.disconnect();
			models.addObject("lists", lists);
		} catch (Exception e) {
			e.getStackTrace();
		}

		return models;
	}
	
	
	@RequestMapping(value = {"/admin/restaurant"})
	public ModelAndView blog(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/restauranthome");	
		String res_adress_area[] = {"강남구","강동구","강서구","강북구","관악구","광진구","구로구","금천구","노원구","동대문구"
									,"도봉구","동작구","마포구","서대문구","성동구","성북구","서초구","송파구","영등포구","용산구"
									,"양천구","은평구","종로구","중구","중랑구"};
		List<String> area = new ArrayList<String>();
		for(int i = 0; i < res_adress_area.length; i++) {
			area.add(res_adress_area[i]);
		}
		List<RestaurantVO> resVO = new ArrayList<RestaurantVO>();
		resVO = ARS.listAll();
		mav.addObject("data",resVO);
		mav.addObject("area",area);
		return mav;
	}
	
	@RequestMapping(value = {"/admin/board"})
	public ModelAndView blogPost(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/board");
		List<EventVO> list = new ArrayList<EventVO>();
		list = AES.list();
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping(value = {"/admin/addrestaurant"})
	public ModelAndView addrestaurant() {
		return new ModelAndView("/admin/addrestaurant");
	}
	
	@RequestMapping(value = {"/admin/addRestaurantOK"})
	public void addRestaurantOK(@RequestParam Map<String,Object> map, HttpServletResponse response) {
		int isCreated =  ARS.create(map);
		if(isCreated ==1) {
			System.out.println("success");
			ResultSendToClient.onlyResultTo(response, isCreated);
		}
		else {
			System.out.println("faile");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = {"/admin/deleteRestaurant"})
	public Object deleteRestaurant() {
		int result = 0;
		// Gson을 활용한 경우
		result = ARS.deleteAll();
		return new Gson().toJson(result);
	}
	
	@RequestMapping(value = {"/admin/regis"})
	public ModelAndView regis() {
		return new ModelAndView("/admin/regis");
	}
	
	@RequestMapping(value = { "/admin/restaurantDetail" })
	public ModelAndView restaurantDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/admin/restaurantdetail");
		String search = request.getParameter("res_code");
		List<RestaurantVO> lists = new ArrayList<RestaurantVO>();
		lists = ARS.listOne(search);
		mav.addObject("data",lists);
		return mav;
	}
	
	@RequestMapping(value = {"/admin/selectRestaurantBySection"})
	public ModelAndView selectSection(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/admin/restauranthome");
		String res_adress_area[] = {"강남구","강동구","강서구","강북구","관악구","광진구","구로구","금천구","노원구","동대문구"
									,"도봉구","동작구","마포구","서대문구","성동구","성북구","서초구","송파구","영등포구","용산구"
									,"양천구","은평구","종로구","중구","중랑구"};
		List<String> area = new ArrayList<String>();
		for(int i = 0; i < res_adress_area.length; i++) {
			area.add(res_adress_area[i]);
			}
		String adress = request.getParameter("res_adress_area");
		List<RestaurantVO> lists = new ArrayList<RestaurantVO>();
		lists = ARS.listBySection(adress);
		mav.addObject("data",lists);
		mav.addObject("area",area);
		return mav;
	}
	
	@RequestMapping(value = {"/admin/reviseAll"})
	public ModelAndView reviseAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/admin/restaurant_revise");
		String code = request.getParameter("res_code");
		List<RestaurantVO> lists = new ArrayList<RestaurantVO>();
		lists = ARS.listOne(code);
		mav.addObject("data",lists);
		return mav;
	}
	
	@RequestMapping(value = {"/admin/reviseAllOK"})
	public void reviseAllOK(@RequestParam Map<String,Object> map,HttpServletRequest request, HttpServletResponse response) throws Exception {
		int isCreated =  ARS.reviseAll(map);
		if(isCreated ==1) {
			System.out.println("success");
			ResultSendToClient.onlyResultTo(response, isCreated);
		}
		else {
			System.out.println("faile");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = {"/admin/deleteOneRestaurant"})
	public Object deleteOneRestaurant(@RequestParam String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int result = 0;
		result = ARS.deleteOne(code);
		return new Gson().toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = { "/admin/event" })
	public ModelAndView adminHome() throws Exception {
		ModelAndView mav = new ModelAndView("/admin/eventhome");
		List<EventVO> lists = new ArrayList<EventVO>();
		List<EventVO> listsResult = new ArrayList<EventVO>();
		try {
			boolean isEnd = false;
			int index = 0;
			String strt = null;
			String end = null;
			BufferedReader rd = null;
			URL url = null;
			HttpURLConnection conn = null;
			String key = "4f645452506b6a6d38307a53726f48";
			while(isEnd == false) {
				StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /* URL */
				urlBuilder.append("/" + URLEncoder.encode(key, "UTF-8")); /* 인증키 (sample사용시에는 호출시 제한됩니다.) */
				urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /* 요청파일타입 (xml,xmlf,xls,json) */
				urlBuilder.append("/" + URLEncoder.encode("culturalEventInfo", "UTF-8")); /* 서비스명 (대소문자 구분 필수입니다.) */
	//
	//			// 즉, 페이지라고 생각하면됩니다 1부터 5까지 출력
				strt = String.valueOf(1+(index*1000));
				end = String.valueOf(((1+index)*1000));
				urlBuilder.append("/" + URLEncoder.encode(strt, "UTF-8")); /* 요청시작위치 (sample인증키 사용시 5이내 숫자) */
				urlBuilder.append("/" + URLEncoder.encode(end, "UTF-8")); /* 요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨) */
	//
	//				urlBuilder.append("/" + URLEncoder.encode("NM_DP","UTF-8"));
	//			// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
	
				// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
				// urlBuilder.append("/" + URLEncoder.encode(nowTime2,"UTF-8")); /* 서비스별 추가
				// 요청인자들*/
	
				url = new URL(urlBuilder.toString());
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Content-type", "application/json");
				System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다. */
				// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
				if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
					rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				} else {
					rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
				}	
				String result = rd.readLine();
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
				JSONObject eventInfoResult = (JSONObject)jsonObject.get("culturalEventInfo");
				JSONArray eventInfo = (JSONArray)eventInfoResult.get("row");
				JSONObject row;
				lists = new ArrayList<EventVO>();
				for(int i=0; i<eventInfo.size(); i++) {
					row = (JSONObject)eventInfo.get(i);
					EventVO eventInfoSet = new EventVO();
					eventInfoSet.setEven_codename(row.get("CODENAME").toString());
					eventInfoSet.setEven_guname(row.get("GUNAME").toString());
					eventInfoSet.setEven_title(row.get("TITLE").toString());
					eventInfoSet.setEven_date(row.get("DATE").toString());
					eventInfoSet.setEven_place(row.get("PLACE").toString());
					eventInfoSet.setEven_org_name(row.get("ORG_NAME").toString());
					eventInfoSet.setEven_use_trgt(row.get("USE_TRGT").toString());
					eventInfoSet.setEven_use_fee(row.get("USE_FEE").toString());
					eventInfoSet.setEven_player(row.get("PLAYER").toString());
					eventInfoSet.setEven_program(row.get("PROGRAM").toString());
					eventInfoSet.setEven_etc_desc(row.get("ETC_DESC").toString());
					eventInfoSet.setEven_org_link(row.get("ORG_LINK").toString());
					eventInfoSet.setEven_main_img(row.get("MAIN_IMG").toString());
					eventInfoSet.setEven_rgst_date(row.get("RGSTDATE").toString());
					eventInfoSet.setEven_ticket(row.get("TICKET").toString());
					eventInfoSet.setEven_strt_date(row.get("STRTDATE").toString());
					eventInfoSet.setEven_end_date(row.get("END_DATE").toString());
					eventInfoSet.setEven_theme_code(row.get("THEMECODE").toString());
					lists.add(eventInfoSet);
				}
				if(eventInfo.size()!=1000) {
					isEnd = true;
				}
				index++;
				listsResult.addAll(lists);
			}
			mav.addObject("data", listsResult);
			rd.close();
			conn.disconnect();
			return mav;
		} catch (Exception e) {
			e.getStackTrace();
			return mav;
		}
	}
	
}