package net.nigne.yzrproject.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.nigne.yzrproject.domain.Criteria;
import net.nigne.yzrproject.service.SupportNoticeService;

@Controller
public class YzrController {
	
	@Autowired
	private SupportNoticeService noticeService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String homeA(Locale locale, Model model) throws Exception {

		return "index";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {

		return "index";
	}
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userPage(Locale locale, Model model) throws Exception {

		return "user/index";
	}
	@RequestMapping(value = "/membership", method = RequestMethod.GET)
	public String membershipPage(Locale locale, Model model) throws Exception {

		return "membership";
	}
	@RequestMapping(value = "/support", method = RequestMethod.GET)
	public ModelAndView supportPage() throws Exception {
		
		Criteria cri=new Criteria();
		cri.setPage(1);
		cri.setArticlePerPage(5);
		ModelAndView view=new ModelAndView();
		view.addObject("notice_list", noticeService.getNoticeList(cri));
		view.setViewName("support/index");
		return view;
	}
}