package com.tour.project.admincontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tour.project.restaurantvo.RestaurantVO;
@Controller
public class AdminAnotherController {

	@RequestMapping(value = {"/admin/about"})
	public ModelAndView about(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/about");
		return mav;
	}
	
	@RequestMapping(value = {"/admin/faq"})
	public ModelAndView faq(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/faq");
		return mav;
	}
	
	@RequestMapping(value = {"/admin/index"})
	public ModelAndView index(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/index");
		return mav;
	}
	
	@RequestMapping(value = {"/admin/portfolioitem"})
	public ModelAndView portfolioitem(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/portfolioitem");
		return mav;
	}
	
	@RequestMapping(value = {"/admin/portfolioOverview"})
	public ModelAndView portfolioOverview(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/portfolioOverview");
		return mav;
	}
	
	@RequestMapping(value = {"/admin/pricing"})
	public ModelAndView pricing(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("/admin/pricing");
		return mav;
	}
}
