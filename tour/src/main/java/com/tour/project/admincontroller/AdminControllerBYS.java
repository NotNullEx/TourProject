package com.tour.project.admincontroller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.tour.project.adminservice.AdminNotificationService;
import com.tour.project.adminservice.AdminRestaurantService;
import com.tour.project.adminservice.AdminTourDataService;
import com.tour.project.adminvo.EventVO;
import com.tour.project.adminvo.NotificationVO;
import com.tour.project.adminvo.RestaurantVO;
import com.tour.project.adminvo.TourVO;
import com.tour.project.common.Criteria;
import com.tour.project.common.ResultSendToClient;
import com.tour.project.common.SuccessResponse;
import com.tour.project.common.vo.PageMakerVO;

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

	@Autowired
	private AdminNotificationService adminNotificationService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminControllerBYS.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/admin/restaurant" })
	public ModelAndView blog(HttpServletRequest request) {
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		if (user_id == null || "".equals(user_id)) {
			return new ModelAndView("/admin/admin_login");
		} else {
			ModelAndView mav = new ModelAndView("/admin/restauranthome");
			String res_adress_area[] = { "강남구", "강동구", "강서구", "강북구", "관악구", "광진구", "구로구", "금천구", "노원구", "동대문구", "도봉구",
					"동작구", "마포구", "서대문구", "성동구", "성북구", "서초구", "송파구", "영등포구", "용산구", "양천구", "은평구", "종로구", "중구", "중랑구" };
			List<String> area = new ArrayList<String>();
			for (int i = 0; i < res_adress_area.length; i++) {
				area.add(res_adress_area[i]);
			}
			List<RestaurantVO> resVO = new ArrayList<RestaurantVO>();
			resVO = ARS.listAll();
			mav.addObject("data", resVO);
			mav.addObject("area", area);
			return mav;
		}
	}

	@RequestMapping(value = { "/admin/board" })
	public ModelAndView board(HttpServletRequest request) {
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		if (user_id == null || "".equals(user_id)) {
			return new ModelAndView("/admin/admin_login");
		} else {
			return new ModelAndView("/admin/board");
		}
	}

	@RequestMapping(value = { "/admin/addrestaurant" })
	public ModelAndView addrestaurant(HttpServletRequest request) {
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		if (user_id == null || "".equals(user_id)) {
			return new ModelAndView("/admin/admin_login");
		} else {
			return new ModelAndView("/admin/addrestaurant");
		}
	}

	@RequestMapping(value = { "/admin/addRestaurantOK" })
	public void addRestaurantOK(@RequestParam Map<String, Object> map, HttpServletResponse response) {
		int isCreated = ARS.create(map);
		if (isCreated == 1) {
			System.out.println("success");
			ResultSendToClient.onlyResultTo(response, isCreated);
		} else {
			System.out.println("faile");
		}
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/deleteRestaurant" })
	public Object deleteRestaurant() {
		int result = 0;
		// Gson을 활용한 경우
		result = ARS.deleteAll();
		return new Gson().toJson(result);
	}

	@RequestMapping(value = { "/admin/regis" })
	public ModelAndView regis(HttpServletRequest request) {
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		if (user_id == null || "".equals(user_id)) {
			return new ModelAndView("/admin/admin_login");
		} else {
			ModelAndView mav = new ModelAndView("/admin/regis");
			List<EventVO> lists = new ArrayList<EventVO>();
			lists = AES.listAll();
			mav.addObject("eventData", lists);
			return mav;
		}
	}

	@RequestMapping(value = { "/admin/restaurantDetail" })
	public ModelAndView restaurantDetail(HttpServletRequest request) throws Exception {
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		if (user_id == null || "".equals(user_id)) {
			return new ModelAndView("/admin/admin_login");
		} else {
			ModelAndView mav = new ModelAndView("/admin/restaurantdetail");
			String search = request.getParameter("res_seq");
			List<RestaurantVO> lists = new ArrayList<RestaurantVO>();
			lists = ARS.listOne(search);
			mav.addObject("data", lists);
			return mav;
		}
	}

	@RequestMapping(value = { "/admin/selectRestaurantBySection" })
	public ModelAndView selectSection(HttpServletRequest request) throws Exception {
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		if (user_id == null || "".equals(user_id)) {
			return new ModelAndView("/admin/admin_login");
		} else {
			ModelAndView mav = new ModelAndView("/admin/restauranthome");
			String res_adress_area[] = { "강남구", "강동구", "강서구", "강북구", "관악구", "광진구", "구로구", "금천구", "노원구", "동대문구", "도봉구",
					"동작구", "마포구", "서대문구", "성동구", "성북구", "서초구", "송파구", "영등포구", "용산구", "양천구", "은평구", "종로구", "중구", "중랑구" };
			List<String> area = new ArrayList<String>();
			for (int i = 0; i < res_adress_area.length; i++) {
				area.add(res_adress_area[i]);
			}
			String adress = request.getParameter("res_adress_area");
			List<RestaurantVO> lists = new ArrayList<RestaurantVO>();
			lists = ARS.listBySection(adress);
			mav.addObject("data", lists);
			mav.addObject("area", area);
			return mav;
		}
	}

	@RequestMapping(value = { "/admin/reviseAll" })
	public ModelAndView reviseAll(HttpServletRequest request) throws Exception {
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		if (user_id == null || "".equals(user_id)) {
			return new ModelAndView("/admin/admin_login");
		} else {
			ModelAndView mav = new ModelAndView("/admin/restaurant_revise");
			String code = request.getParameter("res_seq");
			List<RestaurantVO> lists = new ArrayList<RestaurantVO>();
			lists = ARS.listOne(code);
			mav.addObject("data", lists);
			return mav;
		}
	}

	@RequestMapping(value = { "/admin/reviseAllOK" })
	public void reviseAllOK(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {
		int isRevised = ARS.reviseAll(map);
		if (isRevised == 1) {
			System.out.println("success");
			ResultSendToClient.onlyResultTo(response, isRevised);
		} else {
			System.out.println("faile");
		}
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/deleteOneRestaurant" })
	public Object deleteOneRestaurant(@RequestParam String code) throws Exception {
		int result = 0;
		result = ARS.deleteOne(code);
		return new Gson().toJson(result);
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/eventDataInsert" })
	public Object adminHome() throws Exception {
		List<EventVO> lists = new ArrayList<EventVO>();
		try {
			boolean isEnd = false;
			int index = 0;
			String strt = null;
			String end = null;
			BufferedReader rd = null;
			URL url = null;
			HttpURLConnection conn = null;
			String key = "4f645452506b6a6d38307a53726f48";
			while (isEnd == false) {
				StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /* URL */
				urlBuilder.append("/" + URLEncoder.encode(key, "UTF-8")); /* 인증키 (sample사용시에는 호출시 제한됩니다.) */
				urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /* 요청파일타입 (xml,xmlf,xls,json) */
				urlBuilder.append("/" + URLEncoder.encode("culturalEventInfo", "UTF-8")); /* 서비스명 (대소문자 구분 필수입니다.) */
				//
				// // 즉, 페이지라고 생각하면됩니다 1부터 5까지 출력
				strt = String.valueOf(1 + (index * 1000));
				end = String.valueOf(((1 + index) * 1000));
				urlBuilder.append("/" + URLEncoder.encode(strt, "UTF-8")); /* 요청시작위치 (sample인증키 사용시 5이내 숫자) */
				urlBuilder.append("/" + URLEncoder.encode(end, "UTF-8")); /* 요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨) */
				//
				// urlBuilder.append("/" + URLEncoder.encode("NM_DP","UTF-8"));
				// // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

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
				JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
				JSONObject eventInfoResult = (JSONObject) jsonObject.get("culturalEventInfo");
				JSONArray eventInfo = (JSONArray) eventInfoResult.get("row");
				JSONObject row;
				lists = new ArrayList<EventVO>();
				int insert = 0;
				for (int i = 0; i < eventInfo.size(); i++) {
					row = (JSONObject) eventInfo.get(i);
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
					insert = AES.create(eventInfoSet);
				}
				System.out.println(eventInfo.size());
				if (eventInfo.size() != 1000) {
					isEnd = true;
				}
				index++;
			}
			rd.close();
			conn.disconnect();
			return new Gson().toJson(lists);
		} catch (Exception e) {
			e.getStackTrace();
			return lists;
		}
	}

	@RequestMapping(value = { "/admin/event" })
	public ModelAndView event(HttpServletRequest request) throws Exception {
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		if (user_id == null || "".equals(user_id)) {
			return new ModelAndView("/admin/admin_login");
		} else {
			ModelAndView mav = new ModelAndView("/admin/eventhome");
			List<EventVO> lists = new ArrayList<EventVO>();
			lists = AES.listAll();
			int total = AES.getTotal();
			Criteria cri = new Criteria(1, 20);
			int numIndex = 1, num = 0;

			if (request.getParameter("num") != null)
				num = Integer.valueOf(request.getParameter("num"));

			if (num > 0) {
				numIndex = num;
				cri.setPageNum(num);
			}

			PageMakerVO pmVO = new PageMakerVO(cri, total);
			int end = numIndex * cri.getAmount();
			int strt = end - cri.getAmount();

			if (end > cri.getAmount())
				strt++;

			mav.addObject("data", lists);
			mav.addObject("page", pmVO);
			mav.addObject("strt", strt);
			mav.addObject("end", end);
			mav.addObject("index", num);
			return mav;
		}
	}

	@RequestMapping(value = { "/admin/addEvent" })
	public ModelAndView addEvent(HttpServletRequest request) {
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		if (user_id == null || "".equals(user_id)) {
			return new ModelAndView("/admin/admin_login");
		} else {
			return new ModelAndView("/admin/addevent");
		}
	}

	@RequestMapping(value = { "/admin/addEventOK" })
	public void addEventOK(@RequestParam Map<String, Object> map, HttpServletResponse response) {
		int isCreated = AES.create(map);
		if (isCreated == 1) {
			System.out.println("success");
			ResultSendToClient.onlyResultTo(response, isCreated);
		} else {
			System.out.println("faile");
		}
	}

	@RequestMapping(value = { "/admin/eventDetail" })
	public ModelAndView eventDetail(HttpServletRequest request) throws Exception {
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		if (user_id == null || "".equals(user_id)) {
			return new ModelAndView("/admin/admin_login");
		} else {
			ModelAndView mav = new ModelAndView("/admin/eventdetail");
			String code = request.getParameter("even_code");
			List<EventVO> lists = new ArrayList<EventVO>();
			lists = AES.listByCode(code);
			mav.addObject("data", lists);
			return mav;
		}
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/deleteEvent" })
	public Object deleteEvent() {
		int result = 0;
		// Gson을 활용한 경우
		result = AES.deleteAll();
		return new Gson().toJson(result);
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/deleteOneEvent" })
	public Object deleteOneEvent(@RequestParam String code) throws Exception {
		int result = 0;
		result = AES.deleteOne(code);
		return new Gson().toJson(result);
	}

	@RequestMapping(value = { "/admin/eventReviseAll" })
	public ModelAndView eventReviseAll(HttpServletRequest request) throws Exception {
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		if (user_id == null || "".equals(user_id)) {
			return new ModelAndView("/admin/admin_login");
		} else {
			ModelAndView mav = new ModelAndView("/admin/event_revise");
			String code = request.getParameter("even_code");
			List<EventVO> lists = new ArrayList<EventVO>();
			lists = AES.listByCode(code);
			mav.addObject("data", lists);
			return mav;
		}
	}

	@RequestMapping(value = { "/admin/eventReviseAllOK" })
	public void eventReviseAllOK(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {
		int isRevised = AES.reviseAll(map);
		if (isRevised == 1) {
			System.out.println("success");
			ResultSendToClient.onlyResultTo(response, isRevised);
		} else {
			System.out.println("faile");
		}
	}

	@RequestMapping(value = { "/admin/createNotification" })
	public ModelAndView createNotification(HttpServletRequest request) throws Exception {
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		if (user_id == null || "".equals(user_id)) {
			return new ModelAndView("/admin/admin_login");
		} else {
			return new ModelAndView("/admin/createnotification");
		}
	}

	@RequestMapping(value = { "/admin/createNotificationOK" })
	public void createNotificationOK(@RequestParam Map<String, Object> map, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		try {
			int admin_seq = (int) request.getSession().getAttribute("SESSION_US_SEQ");
			map.put("admin_seq", admin_seq);
			int isCreated = adminNotificationService.create(map);
			if (isCreated == 1) {
				System.out.println("success");
				ResultSendToClient.onlyResultTo(response, isCreated);
			} else {
				System.out.println("faile");
			}
		} catch (Exception e) {
			LOGGER.error("insert error = "+ e.getMessage());
			throw new Exception();
		}
	}
	
	@RequestMapping(value = {"/admin/notificationDetail"})
	public ModelAndView notificationDetail(HttpServletRequest request) throws Exception {
		String noti_seq = request.getParameter("noti_seq");
		NotificationVO noti = adminNotificationService.getNotiDetailList(noti_seq);
		ModelAndView mav = new ModelAndView("/admin/notificationdetail");
		mav.addObject("data", noti);
		return mav;
	}
	
	@RequestMapping(value = {"/admin/notificationUpdate"})
	public ModelAndView dataUpdate(HashMap<String, Object> map, HttpServletRequest request, HttpServletResponse resonse) throws Exception {
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		if (user_id == null || "".equals(user_id)) {
			return new ModelAndView("/admin/admin_login");
		} else {
		ModelAndView mav = new ModelAndView("/admin/notificationupdate");
		String noti_seq = request.getParameter("noti_seq");
		NotificationVO noti = adminNotificationService.getNotiDetailList(noti_seq);
		mav.addObject("data",noti);
		return mav;
		}
	}
	
	@RequestMapping(value = { "/admin/notificationUpdateOK" })
	public void notificationUpdateOK(@RequestParam Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws Exception {
		int admin_seq = (int) request.getSession().getAttribute("SESSION_US_SEQ");
		map.put("admin_seq", admin_seq);
		int isRevised = adminNotificationService.notiUpdate(map);
		if (isRevised == 1) {
			System.out.println("success");
			ResultSendToClient.onlyResultTo(response, isRevised);
		} else {
			System.out.println("faile");
		}
	}
	
	
	@RequestMapping(value = {"/admin/setNotiHidden"})
	@ResponseBody
	public SuccessResponse setNotiHidden(@RequestParam Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		if (user_id == null || "".equals(user_id)) {
			//response.sendRedirect("/admin/admin_login");
			return new SuccessResponse(response.SC_FORBIDDEN, "게시판 표시상태 수정 실패", null);
		} else {
			int noti_status = Integer.valueOf(request.getParameter("noti_status"));
			int admin_seq = (int) request.getSession().getAttribute("SESSION_US_SEQ");
			map.put("noti_status", noti_status);
			map.put("admin_seq", admin_seq);
			int isNotiHidden = adminNotificationService.setNotiHidden(map);
			if(isNotiHidden == 1) {
				System.out.println("success");
				//'ResultSendToClient.onlyResultTo(response, isNotiHidden);
				if (noti_status == 0) return new SuccessResponse(response.SC_OK, "게시판이 표시상태로 변경되었습니다.", null);
				else return new SuccessResponse(response.SC_OK, "게시판이 삭제되었습니다.", null);
			} else {
				return new SuccessResponse(response.SC_BAD_REQUEST, "게시판 표시상태 수정 실패", null);
			}
		}
	}
}