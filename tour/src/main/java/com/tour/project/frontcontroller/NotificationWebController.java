package com.tour.project.frontcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tour.project.adminservice.AdminNotificationService;
import com.tour.project.adminvo.NotificationVO;
import com.tour.project.common.PageMaker;
import com.tour.project.common.StringUtil;
import com.tour.project.common.vo.PageCriteriaVO;

@Controller
@RequestMapping("/front/notification")
public class NotificationWebController {

	@Autowired
	private AdminNotificationService adminNotificationService;
	
	@GetMapping("/list")
	@Transactional(readOnly = true)
	public String getNotificationList(HttpServletRequest request,Model model, HttpServletResponse response,PageCriteriaVO cri) throws Exception {
		
		List<NotificationVO> notiList = adminNotificationService.pagingNotiList(cri);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		int total = adminNotificationService.getTotal();
		pageMaker.setTotalCount(total);
		if(!StringUtil.isEmpty(notiList)) {
			model.addAttribute("list", notiList);
		}
		model.addAttribute("curPage",cri.getPage());
		model.addAttribute("totalCount", total);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/front/notification";
	}
	
	@GetMapping("/detail")
	@Transactional(readOnly = true)
	public String getNotiDetail(@RequestParam("noti_seq") String noti_seq, HttpServletRequest request, Model model) throws Exception {
		
		NotificationVO noti = adminNotificationService.getNotiDetailList(noti_seq);
		
		model.addAttribute("noti", noti);
		
		return "/front/notification_detail";
	}
}
