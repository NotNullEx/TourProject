package com.tour.project.batch;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tour.project.batch.service.SchedulerService;
import com.tour.project.common.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Scheduler {

	@Autowired
	private SchedulerService schedulerService;
	
	// error발생시 rollback
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class, SQLException.class},readOnly = false)
	@Scheduled(cron = "0 0 0 * * *") // 매일자정
	public void updateRecommendTourList() throws Exception {
		
		log.info("====== Scheduler start =======");
		
		List<Map<String, Object>> list = schedulerService.getFavoriteTourList();
		
		//전체 삭제 후 재 insert
		schedulerService.deleteRecommendTourData();
		
		if (!StringUtil.isEmpty(list)) {
			schedulerService.insertRecommendTourData(list);
		}
		
		log.info("====== Scheduler end =======");
		
	}
}
