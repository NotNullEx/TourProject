package com.tour.project.admincontroller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.tour.project.adminservice.AdminEventService;
import com.tour.project.adminservice.AdminNotificationService;
import com.tour.project.adminservice.AdminRestaurantService;
import com.tour.project.adminservice.AdminTourDataService;
import com.tour.project.adminvo.EventVO;
import com.tour.project.adminvo.NotificationVO;
import com.tour.project.adminvo.RestaurantVO;
import com.tour.project.common.PageMaker;
import com.tour.project.common.ResultSendToClient;
import com.tour.project.common.SuccessResponse;
import com.tour.project.common.UtilClass;
import com.tour.project.common.service.GunameService;
import com.tour.project.common.vo.GunameVO;
import com.tour.project.common.vo.PageCriteriaVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminControllerBYS {

	@Autowired
	private AdminRestaurantService adminRestaurantService;

	@Autowired
	private AdminEventService adminEventService;

	@Autowired
	private AdminNotificationService adminNotificationService;
	
	@Autowired
	private GunameService gunameService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminControllerBYS.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/admin/restaurant" })
	public ModelAndView restaurant(HttpServletRequest request, PageCriteriaVO cri) throws Exception {
		ModelAndView mav = new ModelAndView("/admin/restauranthome");
		int pagingList = 0; 
		List<RestaurantVO> resVO = adminRestaurantService.listAll(cri);
		List<GunameVO> area = gunameService.gunameList();
		PageMaker pageMaker = new PageMaker();
		String device = UtilClass.isDevice(request);
		if ("MOBILE".equals(device)) {
			pageMaker.setDisplayPageNum(5);
		}
		pageMaker.setCri(cri);
		pagingList = adminRestaurantService.getRestaurantTotal();
		pageMaker.setTotalCount(pagingList);
		if(resVO != null && resVO.size() > 0) {
			mav.addObject("data", resVO);
		}
		mav.addObject("area", area);
		mav.addObject("curPage", cri.getPage());
		mav.addObject("totalCount", pagingList);
		mav.addObject("pageMaker", pageMaker);
		return mav;
	}

	@RequestMapping(value = { "/admin/addrestaurant" })
	public String addrestaurant(HttpServletRequest request, Model model) throws Exception {
		List<GunameVO> area = gunameService.gunameList();
		model.addAttribute("area", area);
		return "/admin/addrestaurant";
	}

	@RequestMapping(value = { "/admin/addRestaurantOK" })
	public void addRestaurantOK(@RequestParam Map<String, Object> map, HttpServletResponse response) {
		int isCreated = adminRestaurantService.create(map);
		if (isCreated == 1) {
			System.out.println("success");
			ResultSendToClient.onlyResultTo(response, isCreated);
		} else {
			System.out.println("faile");
		}
	}

	@RequestMapping(value = { "/admin/regis" })
	public ModelAndView regis(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/admin/regis");
		List<EventVO> lists = new ArrayList<EventVO>();
		lists = adminEventService.listAll();
		mav.addObject("eventData", lists);
		return mav;
	}

	@RequestMapping(value = { "/admin/restaurantDetail" })
	public ModelAndView restaurantDetail(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/admin/restaurantdetail");
		String search = request.getParameter("res_code");
		List<RestaurantVO> lists = new ArrayList<RestaurantVO>();
		lists = adminRestaurantService.listOne(search);
		mav.addObject("data", lists);
		return mav;
	}

	@RequestMapping(value = { "/admin/selectRestaurantBySection" })
	public ModelAndView selectSection(HttpServletRequest request, PageCriteriaVO cri) throws Exception {
		ModelAndView mav = new ModelAndView("/admin/restauranthome");
		int pagingList = 0;
		String adress = request.getParameter("res_adress_area");
		List<GunameVO> area = gunameService.gunameList();
		HashMap<String, Object> map = new HashMap<String, Object>();
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pagingList = adminRestaurantService.getRestaurantTotalBySection(adress);
		pageMaker.setTotalCount(pagingList);
		map.put("adress", adress);
		map.put("pageStart",  cri.getPageStart());
		map.put("perPageNum", cri.getPerPageNum());
		List<RestaurantVO> lists = adminRestaurantService.listBySection(map);
		if(lists != null && lists.size() > 0) {
			mav.addObject("data", lists);
		}
		mav.addObject("area", area);
		mav.addObject("curPage", cri.getPage());
		mav.addObject("totalCount", pagingList);
		mav.addObject("pageMaker", pageMaker);
		mav.addObject("address", adress);
		return mav;
	}

	@RequestMapping(value = { "/admin/reviseAll" })
	public ModelAndView reviseAll(@RequestParam String res_code) throws Exception {
		ModelAndView mav = new ModelAndView("/admin/restaurant_revise");
		List<RestaurantVO> lists = new ArrayList<RestaurantVO>();
		lists = adminRestaurantService.listOne(res_code);
		List<GunameVO> area = gunameService.gunameList();
		mav.addObject("area", area);
		mav.addObject("data", lists);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = { "/admin/reviseAllOK" })
	public void reviseAllOK(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {
		int isRevised = adminRestaurantService.reviseAll(map);
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
		result = adminRestaurantService.deleteOne(code);
		return new Gson().toJson(result);
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/eventDataInsert" })
	public Object eventDataInsert() throws Exception {
		List<EventVO> lists = new ArrayList<EventVO>();
		try {
			BufferedReader rd = null;
			URL url = null;
			HttpURLConnection conn = null;
			String key = "4f645452506b6a6d38307a53726f48";
			String key_url = "http://openapi.seoul.go.kr:8088";
			StringBuilder urlBuilder = new StringBuilder();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String date = formatter.format(cal.getTime());
			int start = 1;
			int end = 1000;
			while(end<=3000) {
				urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /* URL */
				urlBuilder.append("/" + URLEncoder.encode(key, "UTF-8")); /* 인증키 (sample사용시에는 호출시 제한됩니다.) */
				urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /* 요청파일타입 (xml,xmlf,xls,json) */
				urlBuilder.append("/" + URLEncoder.encode("culturalEventInfo", "UTF-8")); /* 서비스명 (대소문자 구분 필수입니다.) */
				urlBuilder.append("/" + URLEncoder.encode(Integer.toString(start), "UTF-8")); /* 요청시작위치 (sample인증키 사용시 5이내 숫자) */
				urlBuilder.append("/" + URLEncoder.encode(Integer.toString(end), "UTF-8")); /* 요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨) */

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
				formatter = new SimpleDateFormat("yyyy-MM-dd");
				
				int insert = 0;
				lists = new ArrayList<EventVO>();
				for (int i = 0; i < eventInfo.size(); i++) {
					
					row = (JSONObject) eventInfo.get(i);
					EventVO eventInfoSet = new EventVO();
					String rdate = row.get("END_DATE").toString();
					System.out.println(rdate);
					System.out.println(date);
					
					// api로 취득한 이벤트종료날짜
					Date rowDate = formatter.parse(rdate);
					// 로컬 날짜
					Date localDate = formatter.parse(date);
					
					// 로컬날짜를 api 이벤트 종료날짜와 비교
					// 조건: 로컬 날짜가 이벤트종료 날짜보다 크면 즉, 현재날짜로부터 이벤트가 종료되었을때
					if(!localDate.after(rowDate)) {
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
				}
				insert = adminEventService.create(lists);
				start += 1000;
				end += 1000;
			}			

			rd.close();
			conn.disconnect();
			return new Gson().toJson(lists);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.getStackTrace();
			throw new Exception();
		}
	}

	@RequestMapping(value = { "/admin/event" })
	public ModelAndView event(HttpServletRequest request,PageCriteriaVO cri) throws Exception {

		ModelAndView mav = new ModelAndView("/admin/eventhome");
		List<EventVO> lists = new ArrayList<EventVO>();
		cri.setPerPageNum(9);
		lists = adminEventService.listAll(cri);
		int total = adminEventService.getTotal();
		PageMaker pageMaker = new PageMaker();
		String device = UtilClass.isDevice(request);
		if ("MOBILE".equals(device)) {
			pageMaker.setDisplayPageNum(5);
		}
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(total);

		mav.addObject("curPage",cri.getPage());
		mav.addObject("totalCount", total);
		mav.addObject("data", lists);
		mav.addObject("pageMaker", pageMaker);
		return mav;
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
		int isCreated = adminEventService.create(map);
		if (isCreated == 1) {
			System.out.println("success");
			ResultSendToClient.onlyResultTo(response, isCreated);
		} else {
			System.out.println("faile");
		}
	}

	@RequestMapping(value = { "/admin/eventDetail" })
	public ModelAndView eventDetail(HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView("/admin/eventdetail");
		String code = request.getParameter("even_code");
		List<EventVO> lists = new ArrayList<EventVO>();
		lists = adminEventService.listByCode(code);
		mav.addObject("data", lists);
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/deleteEvent" })
	public Object deleteEvent() {
		int result = 0;
		// Gson을 활용한 경우
		result = adminEventService.deleteAll();
		return new Gson().toJson(result);
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/deleteOneEvent" })
	public Object deleteOneEvent(@RequestParam String code) throws Exception {
		int result = 0;
		result = adminEventService.deleteOne(code);
		return new Gson().toJson(result);
	}

	@RequestMapping(value = { "/admin/eventReviseAll" })
	public ModelAndView eventReviseAll(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/admin/event_revise");
		String code = request.getParameter("even_code");
		List<EventVO> lists = new ArrayList<EventVO>();
		lists = adminEventService.listByCode(code);
		mav.addObject("data", lists);
		return mav;
	}

	@RequestMapping(value = { "/admin/eventReviseAllOK" })
	public void eventReviseAllOK(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {
		int isRevised = adminEventService.reviseAll(map);
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
			int admin_seq = (int) request.getSession().getAttribute("ADMIN_US_SEQ");
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
		ModelAndView mav = new ModelAndView("/admin/notificationupdate");
		String noti_seq = request.getParameter("noti_seq");
		NotificationVO noti = adminNotificationService.getNotiDetailList(noti_seq);
		mav.addObject("data",noti);
		return mav;
	}

	@RequestMapping(value = { "/admin/notificationUpdateOK" })
	public void notificationUpdateOK(@RequestParam Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws Exception {
		int admin_seq = (int) request.getSession().getAttribute("ADMIN_US_SEQ");
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
//		return new SuccessResponse(response.SC_FORBIDDEN, "게시판 표시상태 수정 실패", null);
		int noti_status = Integer.valueOf(request.getParameter("noti_status"));
		int admin_seq = (int) request.getSession().getAttribute("ADMIN_US_SEQ");
		map.put("noti_status", noti_status);
		map.put("admin_seq", admin_seq);
		int isNotiHidden = adminNotificationService.setNotiHidden(map);
		if(isNotiHidden == 1) {
			System.out.println("success");
			//'ResultSendToClient.onlyResultTo(response, isNotiHidden);
			if (noti_status == 0) return new SuccessResponse(response.SC_OK, "게시판이 표시상태로 변경되었습니다.", null);
			else return new SuccessResponse(response.SC_OK, "게시판이 비표시상태로 변경되었습니다.", null);
		} else {
			return new SuccessResponse(response.SC_BAD_REQUEST, "게시판 표시상태 수정 실패", null);
		}
	}
}