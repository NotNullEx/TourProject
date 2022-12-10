package com.tour.project.admincontroller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tour.project.adminservice.AdminEventService;
import com.tour.project.adminservice.AdminTourDataService;
import com.tour.project.adminvo.EventVO;
import com.tour.project.adminvo.TourVO;
import com.tour.project.common.UtilClass;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/excel")
@Slf4j
public class AdminExcelController {

	@Autowired
	private AdminTourDataService service;
	
	@Autowired
	private AdminEventService adminEventService;
	
	@GetMapping(value = "/download/tourList")
	@Transactional(readOnly = true)
	public void downloadTourExcelList(HttpServletResponse response) throws Exception {
		Workbook wb = null;
		try {
			response.setCharacterEncoding("UTF-8");
			List<TourVO> list = service.getTourListAll();
			
			String[] headers = {
					"관광지 시퀀스",
					"관광API 고유번호",
					"팩스 번호",
					"주소",
					"도로명 주소",
					"교통정보",
					"홈페이지 정보",
					"전화번호",
					"영업일",
					"휴식일",
					"이용시간",
					"관광지명",
					"소재지(구)"
			};
			
			String[] keys = {
					"tour_seq"
					,"tour_post_sn"
					,"tour_cmmn_fax"
					,"tour_address"
					,"tour_new_address"
					,"tour_subway_info"
					,"tour_cmmn_hmpg_url"
					,"tour_cmmn_telno"
					,"tour_cmmn_bsnde"
					,"tour_cmmn_rstde"
					,"tour_cmmn_use_time"
					,"tour_post_sj"
					,"tour_gu_name"
			};
			
			// 엑셀 다운로드
			response.setContentType("ms-vnd/excel");
			response.setHeader("Content-Disposition", "attachment; filename="+ "tourList.xlsx");
			
			ObjectMapper mapper = new ObjectMapper();
			List<LinkedHashMap<String, Object>> dataList = new ArrayList<LinkedHashMap<String,Object>>();
			for (TourVO tour : list) {
				LinkedHashMap map = mapper.convertValue(tour, LinkedHashMap.class);
				
				dataList.add(map);
			}
			
			wb = UtilClass.excelSupportUtil("관광지 정보",headers,keys, dataList);
			
			wb.write(response.getOutputStream());
			
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (wb != null) {
				wb.close();
			}
		}
	}
	
	@GetMapping(value = "/download/eventList")
	@Transactional(readOnly = true)
	public void downloadEventExcelList(HttpServletResponse response) throws Exception {
		Workbook wb = null;
		try {
			response.setCharacterEncoding("UTF-8");
			List<EventVO> list = adminEventService.getEventlist();

			String[] headers = {
					"이벤트 시퀀스",
					"이벤트 종류",
					"이벤트 구이름",
					"이벤트 명",
					"이벤트 기간",
					"이벤트 장소",
					"이벤트 타겟",
					"이벤트 홈페이지"
			};
			
			String[] keys = {
					"even_code",
					"even_codename",
					"even_guname",
					"even_title",
					"even_date",
					"even_place",
					"even_use_trgt",
					"even_org_link"
			};
			
			// 엑셀 다운로드
			response.setContentType("ms-vnd/excel");
			response.setHeader("Content-Disposition", "attachment; filename="+ "eventList.xlsx");
			
			ObjectMapper mapper = new ObjectMapper();
			List<LinkedHashMap<String, Object>> dataList = new ArrayList<LinkedHashMap<String,Object>>();
			for (EventVO event : list) {
				LinkedHashMap map = mapper.convertValue(event, LinkedHashMap.class);
				
				dataList.add(map);
			}
			
			wb = UtilClass.excelSupportUtil("이벤트 정보",headers,keys, dataList);
			
			wb.write(response.getOutputStream());
			
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (wb != null) {
				wb.close();
			}
		}
	}
}
