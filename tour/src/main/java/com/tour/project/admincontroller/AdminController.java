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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tour.project.adminservice.AdminTourDataService;
import com.tour.project.adminvo.TourVO;
import com.tour.project.common.ResultSendToClient;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {

	@Autowired
	private AdminTourDataService service;

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	
	@RequestMapping(value = { "/admin/regis" })
	public ModelAndView registration() throws Exception {
		ModelAndView mav = new ModelAndView("/admin/regis");
		List<TourVO> lists = new ArrayList<TourVO>();
		lists = service.tourList();

		mav.addObject("sb", lists);
		return mav;
	}
	
	
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
						tourInfoSet.setTour_cmmn_hmpg_url(row.get("CMMN_HMPG_URL").toString());
					}
					tourInfoSet.setTour_cmmn_telno(row.get("CMMN_TELNO").toString());
					tourInfoSet.setTour_cmmn_bsnde(row.get("CMMN_BSNDE").toString());
					tourInfoSet.setTour_bf_desc(row.get("BF_DESC").toString());
					tourInfoSet.setTour_cmmn_rstde(row.get("CMMN_RSTDE").toString());
					tourInfoSet.setTour_cmmn_use_time(row.get("CMMN_USE_TIME").toString());
					tourInfoSet.setTour_post_sn(row.get("POST_SN").toString());
					
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
			e.getStackTrace();
			return lists;
		}
	}

	@RequestMapping(value = { "/admin" })
	public ModelAndView dataInsert() throws Exception {
		ModelAndView models = new ModelAndView("/admin/home");
		List<TourVO> lists = new ArrayList<TourVO>();
		lists = service.tourList();

		models.addObject("sb", lists);
		return models;
	}
	
	@RequestMapping(value = { "/admin/tourDetail" })
	public ModelAndView tourDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView models = new ModelAndView("/admin/tourdetail");
		String tour_seq = request.getParameter("tour_seq");
		List<TourVO> lists = new ArrayList<TourVO>();
		lists = service.tourOneList(tour_seq);
		String[] result = lists.get(0).getTour_address().split(" ");
		String address = result[2];

		models.addObject("sb",lists);
		models.addObject("address",address);
		return models;
	}
}