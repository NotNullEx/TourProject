package com.tour.project.frontcontroller;

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
import com.tour.project.admincontroller.AdminControllerBYS;
import com.tour.project.adminservice.AdminBoardService;
import com.tour.project.adminservice.AdminEventService;
import com.tour.project.adminservice.AdminRestaurantService;
import com.tour.project.adminvo.BoardVO;
import com.tour.project.adminvo.EventVO;
import com.tour.project.adminvo.NotificationVO;
import com.tour.project.adminvo.RestaurantVO;
import com.tour.project.common.PageMaker;
import com.tour.project.common.ResultSendToClient;
import com.tour.project.common.StringUtil;
import com.tour.project.common.UtilClass;
import com.tour.project.common.vo.PageCriteriaVO;
import com.tour.project.frontservice.FrontBoardService;
import com.tour.project.frontservice.FrontComentsService;
import com.tour.project.frontservice.MemberLoginService;
import com.tour.project.frontservice.MemberUpdateService;
import com.tour.project.frontvo.ComentsVO;
import com.tour.project.frontvo.MemberVO;

@Controller
public class FrontControllerKJM {
	
	@Autowired
	private AdminRestaurantService infoService;
	
	@Autowired
	private AdminEventService AES;
	
	@Autowired
	private FrontBoardService FBS;
	
	@Autowired
	private FrontComentsService frontComentsService;
	
	@Autowired
	private MemberLoginService loginService;
	
	@Autowired
	private MemberUpdateService memberUpdateService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontControllerKJM.class);
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
	
	
	@RequestMapping(value = {"/front/board_detail"})
	public ModelAndView blogPostDetail(Locale locale, Model model, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/front/board_detail");
		List<BoardVO> bv = new ArrayList<BoardVO>();
		String seq = request.getParameter("board_seq");
		int mem_seq = 0;
		if(request.getSession().getAttribute("SESSION_US_SEQ") != null && (Integer)request.getSession().getAttribute("SESSION_US_SEQ") != 0) 
			mem_seq = (Integer)request.getSession().getAttribute("SESSION_US_SEQ");
		bv = FBS.listBySeq(seq);
		List<ComentsVO> list = new ArrayList<ComentsVO>();
		if(frontComentsService.comentsByBoard(seq) != null) list = frontComentsService.comentsByBoard(seq);
		if(list != null) mav.addObject("data", list);
		if(mem_seq != 0) mav.addObject("mem_seq", mem_seq);
		mav.addObject("list", bv);
		return mav;
	}

	
	@RequestMapping(value = {"/front/board"})
	public ModelAndView blogPost(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/front/board");
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
	public void createBoardOK(@RequestParam Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) {
		int mem_seq = 0;
		mem_seq = (Integer)request.getSession().getAttribute("SESSION_US_SEQ");
		map.put("mem_seq", mem_seq);
		int isCreated =  FBS.create(map);
		if(isCreated ==1) {
			System.out.println("success");
			ResultSendToClient.onlyResultTo(response, isCreated);
		}
		else {
			System.out.println("faile");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = {"/front/createComents"})
	public void createComents(@RequestParam Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws Exception {
		try {
			String board_seq = request.getParameter("board_seq");
			String mem_email = (String)request.getSession().getAttribute("MEMBER_ID");
			int mem_seq = (Integer)request.getSession().getAttribute("SESSION_US_SEQ");
			
			map.put("mem_email", mem_email);
			map.put("mem_seq", mem_seq);
			map.put("board_seq", board_seq);
			int isCreated = frontComentsService.create(map);
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
	
	@ResponseBody
	@RequestMapping(value = {"/front/reviseComents"})
	public void reviseComents(@RequestParam Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			int isRevised = frontComentsService.reviseComents(map);
			if(isRevised == 1) {
				System.out.println("success");
				ResultSendToClient.onlyResultTo(response, isRevised);
			}else {
				System.out.println("faile");
			}
		} catch (Exception e) {
			LOGGER.error("insert error = "+ e.getMessage());
			throw new Exception();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = {"/front/deleteComents"})
	public void deleteComents(@RequestParam String seq, HttpServletResponse response, HttpServletRequest request) throws Exception {
		try {
			String coment_seq = seq;	
			int isDeleted = frontComentsService.deleteComentsWithSeq(coment_seq);
			if (isDeleted == 1) {
				System.out.println("success");
				ResultSendToClient.onlyResultTo(response, isDeleted);
			} else {
				System.out.println("faile");
			}
		} catch (Exception e) {
			LOGGER.error("insert error = "+ e.getMessage());
			throw new Exception();
		}
	}
	
	
	@RequestMapping(value = {"/front/restaurant"})
	public ModelAndView blog(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/front/restaurant");	
		List<RestaurantVO> resVO = new ArrayList<RestaurantVO>();
		resVO = infoService.listAll();
		mav.addObject("data",resVO);
		return mav;
	}	
	
	
	@RequestMapping(value = {"/front/event"})
	public ModelAndView contact() {
		ModelAndView mav = new ModelAndView("/front/event");
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
	
	@RequestMapping(value = { "/front/myPage" })
	public ModelAndView mypage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("/front/mypage");
	}
	
	@RequestMapping(value = { "/front/myInfo" })
	public ModelAndView myInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String memberId = (String) request.getSession().getAttribute("MEMBER_ID");
		ModelAndView mav = new ModelAndView("/front/myinfo");
		MemberVO rtnVal = loginService.memberInfo(memberId);
		mav.addObject("data" , rtnVal);
		return mav;
	}
	
	@RequestMapping(value = { "/front/myInfoUpdate" })
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
		result = memberUpdateService.memberUpdate(map);
		return new Gson().toJson(result);
	}
	
	@RequestMapping(value = { "/front/myBoardInfo" })
	public ModelAndView myNotiInfo(HttpServletRequest request, HttpServletResponse response,PageCriteriaVO cri) throws Exception {
		ModelAndView mav = new ModelAndView("/front/myboardinfo");
		
		String user_id = (String) request.getSession().getAttribute("MEMBER_ID");
		if(user_id == null || "".equals(user_id)) {
			return new ModelAndView("/front/member_login");
		}else {
			int memberSeq = (int) request.getSession().getAttribute("SESSION_US_SEQ");
			List<BoardVO> list = new ArrayList<BoardVO>();
			int pagingList = 0; 
			HashMap<String, Object> map = new HashMap<String, Object>();
			PageMaker pageMaker = new PageMaker();
			map.put("seq", memberSeq);
			map.put("pageStart",  cri.getPageStart());
			map.put("perPageNum", cri.getPerPageNum());
			pageMaker.setCri(cri);
			list = FBS.myBoardList(map);
			pagingList = FBS.getMyBoardTotal(memberSeq);
			pageMaker.setTotalCount(pagingList);
			
			mav.addObject("totalCount", pagingList);
			mav.addObject("curPage",cri.getPage());
			mav.addObject("seq",memberSeq);
			mav.addObject("list", list);
			mav.addObject("pageMaker", pageMaker);
			
		}
		return mav;
	}
	

}
