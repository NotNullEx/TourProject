package com.tour.project.batch;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tour.project.batch.service.SchedulerService;
import com.tour.project.common.StringUtil;

@Controller
@RequestMapping("/scheduler")
public class SchedulerController {

	@Autowired
	private SchedulerService schedulerService;
	
	@GetMapping(value = "/updateRecommendTourList")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class, SQLException.class},readOnly = false)
	public void updateRecommendTourList() throws Exception {
		List<Map<String, Object>> list = schedulerService.getFavoriteTourList();
		
		//전체 삭제 후 재 insert
		schedulerService.deleteRecommendTourData();
		
		if (!StringUtil.isEmpty(list)) {
			schedulerService.insertRecommendTourData(list);
		}
		
	}
}
