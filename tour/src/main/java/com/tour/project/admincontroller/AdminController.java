package com.tour.project.admincontroller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.tour.project.adminservice.AdminNotificationService;
import com.tour.project.adminservice.AdminTourDataService;
import com.tour.project.adminservice.AdminUserUpdateService;
import com.tour.project.adminservice.LoginService;
import com.tour.project.adminvo.NotificationVO;
import com.tour.project.adminvo.TourVO;
import com.tour.project.common.PageMaker;
import com.tour.project.common.StringUtil;
import com.tour.project.common.UtilClass;
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

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	
	@ResponseBody
	@RequestMapping(value = { "/admin/dataInsert" })
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
//
//				urlBuilder.append("/" + URLEncoder.encode("NM_DP","UTF-8"));
//			// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

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
	
	@ResponseBody
	@RequestMapping(value = { "/admin/adminDel" })
	public int adminDel(HttpServletRequest request, HttpServletResponse response,PageCriteriaVO cri) throws Exception {
		String emails = request.getParameter("emails");
		int result= 0;
		result = adminService.amindDel(emails);
		
		return result;
	}
}