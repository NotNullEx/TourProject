package com.tour.project.admincontroller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.tour.project.adminservice.AdminEventService;
import com.tour.project.adminservice.AdminNotificationService;
import com.tour.project.adminservice.AdminRestaurantService;
import com.tour.project.adminservice.AdminTourDataService;
import com.tour.project.adminservice.AdminUserUpdateService;
import com.tour.project.adminservice.LoginService;
import com.tour.project.adminvo.EventVO;
import com.tour.project.adminvo.NotificationVO;
import com.tour.project.adminvo.RestaurantVO;
import com.tour.project.adminvo.TourVO;
import com.tour.project.common.PageMaker;
import com.tour.project.common.ResultSendToClient;
import com.tour.project.common.StringUtil;
import com.tour.project.common.SuccessResponse;
import com.tour.project.common.UtilClass;
import com.tour.project.common.service.GunameService;
import com.tour.project.common.vo.GunameVO;
import com.tour.project.common.vo.PageCriteriaVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {

	@Autowired
	private AdminTourDataService service;
	
	@Autowired
	private LoginService adminService;
	
	@Autowired
	private AdminNotificationService adminNotificationService;
	
	@Autowired
	private AdminUserUpdateService adminUserUpdateService;
	
	@Autowired
	private AdminRestaurantService adminRestaurantService;

	@Autowired
	private AdminEventService adminEventService;
	
	@Autowired
	private GunameService gunameService;

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	
	@ResponseBody
	@RequestMapping(value = { "/admin/data/dataInsert" })
	public Object adminHome() throws Exception {
		List<TourVO> lists = new ArrayList<TourVO>();
		try {

			String key = "4f645452506b6a6d38307a53726f48";
			StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /* URL */
			urlBuilder.append("/" + URLEncoder.encode(key, "UTF-8")); /* 인증키 (sample사용시에는 호출시 제한됩니다.) */
			urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /* 요청파일타입 (xml,xmlf,xls,json) */
			urlBuilder.append("/" + URLEncoder.encode("TbVwAttractions", "UTF-8")); /* 서비스명 (대소문자 구분 필수입니다.) */
//
//			// 즉, 페이지라고 생각하면됩니다 1부터 5까지 출력
			urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8")); /* 요청시작위치 (sample인증키 사용시 5이내 숫자) */
			urlBuilder.append("/" + URLEncoder.encode("1000", "UTF-8")); /* 요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨) */

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
			String result = rd.readLine();
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
			JSONObject tourInfoResult = (JSONObject)jsonObject.get("TbVwAttractions");
			JSONArray tourInfo = (JSONArray)tourInfoResult.get("row");
			JSONObject row;
			lists = new ArrayList<TourVO>();
			int insert = 0;
			for(int i=0; i<tourInfo.size(); i++) {
				row = (JSONObject)tourInfo.get(i);
				if(row.get("LANG_CODE_ID").equals("ko")) {
					TourVO tourInfoSet = new TourVO();
					tourInfoSet.setTour_post_sj(row.get("POST_SJ").toString());
					tourInfoSet.setTour_cmmn_fax(row.get("CMMN_FAX").toString());
					tourInfoSet.setTour_address(row.get("ADDRESS").toString());
					tourInfoSet.setTour_new_address(row.get("NEW_ADDRESS").toString());
					tourInfoSet.setTour_subway_info(row.get("SUBWAY_INFO").toString());
					if("".equals(row.get("CMMN_HMPG_URL").toString()) || row.get("CMMN_HMPG_URL").toString() ==null) {
						tourInfoSet.setTour_cmmn_hmpg_url("none");
					}else {
						if(!row.get("CMMN_HMPG_URL").toString().contains("https://")) {
							String http = "https://";
							tourInfoSet.setTour_cmmn_hmpg_url(http + row.get("CMMN_HMPG_URL").toString());
						}else {
							tourInfoSet.setTour_cmmn_hmpg_url(row.get("CMMN_HMPG_URL").toString());
						}
					}
					tourInfoSet.setTour_cmmn_telno(row.get("CMMN_TELNO").toString());
					tourInfoSet.setTour_cmmn_bsnde(row.get("CMMN_BSNDE").toString());
					tourInfoSet.setTour_bf_desc(row.get("BF_DESC").toString());
					tourInfoSet.setTour_cmmn_rstde(row.get("CMMN_RSTDE").toString());
					tourInfoSet.setTour_cmmn_use_time(row.get("CMMN_USE_TIME").toString());
					tourInfoSet.setTour_post_sn(row.get("POST_SN").toString());
					
					if(tourInfoSet.getTour_address() != null && !"".equals(tourInfoSet.getTour_address())) {
						String[] tour_gu = tourInfoSet.getTour_address().split(" ");
						String gu_name = tour_gu[2];
						tourInfoSet.setTour_gu_name(gu_name);
					}
					
					lists.add(tourInfoSet);
					if (tourInfo != null) {
						insert = service.tourInsert(tourInfoSet);
						log.info("insert log : " + insert);
					}
				}
				
			}

			rd.close();
			conn.disconnect();
			return new Gson().toJson(lists);
		} catch (Exception e) {
		
			log.error(e.getMessage());
			throw new Exception();
		}
	}

	@RequestMapping(value = { "/admin" })
	public ModelAndView dataInsert(PageCriteriaVO cri,HttpServletRequest request) throws Exception {
		ModelAndView models = new ModelAndView("/admin/home");
		List<NotificationVO> notiList = adminNotificationService.pagingNotiList(cri);
		PageMaker pageMaker = new PageMaker();
		String device = UtilClass.isDevice(request);
		if ("MOBILE".equals(device)) {
			pageMaker.setDisplayPageNum(5);
		}
		pageMaker.setCri(cri);
		int total = adminNotificationService.getTotal();
		pageMaker.setTotalCount(total);
		if(notiList != null && notiList.size() > 0) {
			models.addObject("list", notiList);
		}
		
		List<HashMap<String, Integer>> memTotal = new ArrayList<HashMap<String,Integer>>();
		HashMap<String, Integer> getContentsTotal = new HashMap<String, Integer>();	
		memTotal = service.memTotal();
		getContentsTotal= service.getContentsTotal();
		// 구해야 하는것
		// 가입자 수
		// 탈퇴자 수
		if(memTotal != null && memTotal.size() >0) {
			models.addObject("signin", memTotal.get(0).get("cnt"));
			models.addObject("signout", memTotal.get(1).get("cnt"));
		}
		// 관광지 수
		// 음식점 수
		// 행사 수
		if(getContentsTotal != null && getContentsTotal.size()>0) {
			models.addObject("tour", getContentsTotal.get("tourcnt"));
			models.addObject("event", getContentsTotal.get("eventcnt"));
			models.addObject("res", getContentsTotal.get("rescnt"));
			models.addObject("board", getContentsTotal.get("boacnt"));
			models.addObject("coments", getContentsTotal.get("comcnt"));
		}
		models.addObject("curPage",cri.getPage());
		models.addObject("totalCount", total);
		models.addObject("pageMaker", pageMaker);
		return models;
	}
	
	@RequestMapping(value = { "/admin/tourDetail" })
	public ModelAndView tourDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView models = new ModelAndView("/admin/tourdetail");
		String tour_seq = request.getParameter("tour_seq");
		List<TourVO> lists = new ArrayList<TourVO>();
		
		try {
			lists = service.tourOneList(tour_seq);

			models.addObject("lists",lists);
			return models;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception();
		}
		
		
	}
	
	@RequestMapping(value = { "/admin/myPage" })
	public ModelAndView mypage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user_id = (String) request.getSession().getAttribute("ADMIN_ID");
		int seq = (int)request.getSession().getAttribute("ADMIN_US_SEQ");
		ModelAndView mav = new ModelAndView("/admin/mypage");
		if(user_id == null || "".equals(user_id)) {
			return new ModelAndView("/admin/admin_login");
		}else {
			mav.addObject("adminId", user_id);
			mav.addObject("seq", seq);
		}
		
		return mav;
	}
	
	@RequestMapping(value = { "/admin/myInfo" })
	public ModelAndView myInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String adminId = (String) request.getSession().getAttribute("ADMIN_ID");
		ModelAndView mav = new ModelAndView("/admin/myinfo");
		Map<String, Object> rtnVal = adminService.amindInfo(adminId);
		String name = (String) rtnVal.get("admin_name");
		String phon = (String) rtnVal.get("admin_phone_num");
		String email = (String) rtnVal.get("admin_email");
		String seq =  Integer.toString((Integer) rtnVal.get("admin_seq"));
		String regist_day = (String) rtnVal.get("admin_regist_day");
		mav.addObject("name" , name);
		mav.addObject("phon" , phon);
		mav.addObject("email" , email);
		mav.addObject("seq" , seq);
		mav.addObject("regist_day" , regist_day);
		
		return mav;
	}
	
	@RequestMapping(value = { "/admin/myInfoUpdate" })
	@ResponseBody
	public Object myInfoUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int result = 0;
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String seq = request.getParameter("seq");
		String pass = request.getParameter("pass");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("phone", phone);
		map.put("seq", seq);
		if(!StringUtil.isEmpty(pass)) {
			String repass = UtilClass.SHA256(pass);
			map.put("pass", repass);
		}
		result = adminUserUpdateService.adminUserUpdate(map);
		return new Gson().toJson(result);
	}
	
	@RequestMapping(value = { "/admin/myNotiInfo" })
	public ModelAndView myNotiInfo(HttpServletRequest request, HttpServletResponse response,PageCriteriaVO cri) throws Exception {
		ModelAndView mav = new ModelAndView("/admin/mynotiinfo");
		String adminId = (String) request.getSession().getAttribute("ADMIN_ID");
		String adminSeq = request.getParameter("admin_seq");
		List<NotificationVO> list = new ArrayList<NotificationVO>();
		int pagingList = 0; 
		HashMap<String, Object> map = new HashMap<String, Object>();
		PageMaker pageMaker = new PageMaker();
		map.put("seq", adminSeq);
		map.put("pageStart",  cri.getPageStart());
		map.put("perPageNum", cri.getPerPageNum());
		map.put("noti", 1);
		pageMaker.setCri(cri);
		list = adminNotificationService.myNotiInfo(map);
		Map<String, Object> rtnVal = adminService.amindInfo(adminId);
		String email = (String) rtnVal.get("admin_email");
		pagingList = adminNotificationService.getmyNotiTotal(adminSeq);
		pageMaker.setTotalCount(pagingList);
		
		mav.addObject("curPage",cri.getPage());
		mav.addObject("totalCount", pagingList);
		mav.addObject("adminId" , email);
		mav.addObject("seq",adminSeq);
		mav.addObject("pageMaker", pageMaker);
		
		try {
			if(list != null && list.size() >0) {
				mav.addObject("list",list);
			}else {
				mav.addObject("msg","등록한 공지사항이 없습니다.");
			}
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return mav;
	}
	
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
	
	@RequestMapping(value = {"/admin/data/addImage"})
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
	
	@RequestMapping(value = {"/admin/data/dataUpdate"})
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
	@RequestMapping(value = {"/admin/data/dataDelete"})
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
	@RequestMapping(value = { "/admin/data/eventDataInsert" })
	public Object eventDataInsert() throws Exception {
		List<EventVO> lists = new ArrayList<EventVO>();
		try {
			BufferedReader rd = null;
			URL url = null;
			HttpURLConnection conn = null;
			String key = "4f645452506b6a6d38307a53726f48";
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
				adminEventService.create(lists);
				start += 1000;
				end += 1000;
			}			

			rd.close();
			conn.disconnect();
			return new Gson().toJson(lists);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.getStackTrace();
			throw new Exception();
		}
	}

	@RequestMapping(value = { "/admin/event" })
	public ModelAndView event(HttpServletRequest request,PageCriteriaVO cri) throws Exception {

		ModelAndView mav = new ModelAndView("/admin/eventhome");
		List<EventVO> lists = new ArrayList<EventVO>();
		List<EventVO> eventData = new ArrayList<EventVO>();
		
		
		cri.setPerPageNum(9);
		lists = adminEventService.listAll(cri);
		eventData = adminEventService.listAll();
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
		mav.addObject("eventData", eventData);
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
	@RequestMapping(value = { "/admin/data/deleteEvent" })
	public Object deleteEvent() {
		int result = 0;
		// Gson을 활용한 경우
		result = adminEventService.deleteAll();
		return new Gson().toJson(result);
	}

	@ResponseBody
	@RequestMapping(value = { "/admin/data/deleteOneEvent" })
	public Object deleteOneEvent(HttpServletRequest request) throws Exception {
		int result = 0;
		String code = request.getParameter("code");
		result = adminEventService.deleteOne(code);
		return new Gson().toJson(result);
	}

	@RequestMapping(value = { "/admin/data/eventReviseAll" })
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
			log.error("insert error = "+ e.getMessage());
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
	public ModelAndView notificationUpdate(HashMap<String, Object> map, HttpServletRequest request, HttpServletResponse resonse) throws Exception {
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